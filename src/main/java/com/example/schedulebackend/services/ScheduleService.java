package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.exceptions.MultipleScheduleException;
import com.example.schedulebackend.model.dto.DisciplineDTO;
import com.example.schedulebackend.model.dto.GroupDTO;
import com.example.schedulebackend.model.dto.ScheduleDTO;
import com.example.schedulebackend.model.dto.TeacherDTO;
import com.example.schedulebackend.model.entities.Schedule;
import com.example.schedulebackend.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

import static java.util.Comparator.comparing;

@Service
public class ScheduleService {

    private final TimeService timeService;
    private final ScheduleRepo scheduleRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final TeacherService teacherService;
    private final String itemName;

    public ScheduleService(ScheduleRepo scheduleRepo,
                           ItemHandler itemHandler,
                           DbaService dbaService,
                           GroupService groupService,
                           DisciplineService disciplineService,
                           TeacherService teacherService,
                           TimeService timeService,
                           @Value("Schedule") String itemName) {
        this.scheduleRepo = scheduleRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.groupService = groupService;
        this.disciplineService = disciplineService;
        this.teacherService = teacherService;
        this.itemName = itemName;
        this.timeService = timeService;
    }

    public void create(ScheduleDTO scheduleDTO) {
        itemHandler.handleIsIdNull(scheduleDTO.getId(), itemName);
        saveToDB(scheduleDTO);
    }

    public void update(ScheduleDTO scheduleDTO) {
        dbaService.get(scheduleRepo, itemName, scheduleDTO.getId());
        saveToDB(scheduleDTO);
    }

    public ScheduleDTO get(long id) {
        Schedule schedule = dbaService.get(scheduleRepo, itemName, id);
        return ScheduleDTO.toDTO(
                schedule,
                TeacherDTO.toDTO(schedule.getTeacher()),
                DisciplineDTO.toDTO(schedule.getDiscipline()),
                GroupDTO.toDTO(schedule.getGroup(), null)
        );
    }

    public void delete(long id) {
        dbaService.get(scheduleRepo, itemName, id);
        scheduleRepo.deleteById(id);
    }

    public List<ScheduleDTO> getAll() {
        return scheduleRepo
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public HashMap<String, List<ScheduleDTO>> getFullSchedule(long id) {
        List<String> days = Arrays.stream(DayOfWeek.values()).filter(x -> x != DayOfWeek.SUNDAY)
                .map(Enum::toString).toList();
        System.out.println("days = " + days);
        List<Schedule> schedules = scheduleRepo.findAllByGroupId(id)
                .stream()
                .filter(schedule -> schedule.getTime() != null)
                .sorted(comparing(Schedule::getTime))
                .toList();
        List<List<ScheduleDTO>> scheduleDays = days.stream()
                .map(day -> setScheduleDay(day, schedules))
                .toList();

        HashMap<String, List<ScheduleDTO>> fullSchedule = new LinkedHashMap<>();
        for (int i = 0; i < days.size(); i++) {
            fullSchedule.put(days.get(i).toLowerCase(), scheduleDays.get(i));
        }
        return fullSchedule;

    }

    public List<ScheduleDTO> setScheduleDay(String weekDay, List<Schedule> schedules) {
        List<ScheduleDTO> result = new ArrayList<>();
        List<String> time = timeService.getStartTime();
        for (String t : time) {
            boolean isExistOnlyOneAnswer = false;
            for (Schedule schedule : schedules) {
                if (Objects.equals(schedule.getTime(), t) &&
                        Objects.equals(schedule.getDayOfWeek().toString(), weekDay)) {
                    if (isExistOnlyOneAnswer) {
                        throw new MultipleScheduleException("There are one more schedule for identical time");
                    }
                    result.add(ScheduleDTO.toDTO(schedule,
                            TeacherDTO.toSimpleDTO(schedule.getTeacher()),
                            null,
                            null));
                    isExistOnlyOneAnswer = true;

                }
            }
            if (!isExistOnlyOneAnswer) {
                result.add(ScheduleDTO.builder().id(0).build());
            }

        }
        return result;
    }

    public void saveToDB(ScheduleDTO scheduleDTO) {
        scheduleRepo.save(Schedule.toEntity(
                scheduleDTO,
                groupService.getGroup(scheduleDTO.getGroupDTO()),
                disciplineService.getDiscipline(scheduleDTO.getDisciplineDTO()),
                teacherService.getTeacher(scheduleDTO.getTeacherDTO())
        ));
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.toDTO(
                schedule,
                TeacherDTO.toDTO(schedule.getTeacher()),
                DisciplineDTO.toDTO(schedule.getDiscipline()),
                GroupDTO.toDTO(schedule.getGroup(), null)
        );
    }


}
