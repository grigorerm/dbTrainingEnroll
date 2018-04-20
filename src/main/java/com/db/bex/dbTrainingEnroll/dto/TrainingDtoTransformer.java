package com.db.bex.dbTrainingEnroll.dto;

import com.db.bex.dbTrainingEnroll.dao.TrainingRepository;
import com.db.bex.dbTrainingEnroll.entity.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingDtoTransformer {

    public TrainingDtoTransformer(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    TrainingRepository trainingRepository;

    public List<TrainingDto> getTrainings(List<Training> training){
        return training.stream().map(this::transform).collect(Collectors.toList());
    }

    public TrainingDto transform(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setName(training.getName());
//        trainingDto.setStartDate(training.getStartDate());
//        trainingDto.setEndDate(training.getEndDate());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String reportDateStart = dateFormat.format(training.getStartDate());
        String reportDateEnd = dateFormat.format(training.getEndDate());

        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String reportStartHour = hourFormat.format(training.getStartDate());
        String reportEndHour = hourFormat.format(training.getEndDate());

        String[] startHourParts = reportStartHour.split(":");
        String startHour = startHourParts[0];
        String startMinute = startHourParts[1];

        String[] endHourParts = reportEndHour.split(":");
        String endHour = endHourParts[0];
        String endMinute = endHourParts[1];

        String[] startParts = reportDateStart.split("/");
        String startDay = startParts[0];
        String startMonth = startParts[1];
        String startYear = startParts[2];

        String[] endParts = reportDateEnd.split("/");
        String endDay = endParts[0];
        String endMonth = endParts[1];
        String endYear = endParts[2];

        if (Integer.parseInt(endYear) - Integer.parseInt(startYear) == 0)
            if (Integer.parseInt(endMonth) - Integer.parseInt(startMonth) == 0) {
                if (Integer.parseInt(endDay) - Integer.parseInt(startDay) == 0) {
                    trainingDto.setDate(reportDateStart);
                    trainingDto.setDuration(Integer.parseInt(startHour));
                } else {
                    trainingDto.setDate(reportDateStart + " - " + reportDateEnd);
                    trainingDto.setDuration(-1);
                }
            } else {
                trainingDto.setDate(reportDateStart + " - " + reportDateEnd);
                trainingDto.setDuration(-1);
            }

        return trainingDto;
    }

}
