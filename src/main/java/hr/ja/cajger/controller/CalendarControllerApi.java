package hr.ja.cajger.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import hr.ja.cajger.model.*;
import hr.ja.cajger.service.CalendarService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/cal")
@RestController
@Slf4j
public class CalendarControllerApi {

    @Autowired
    CalendarService calendarService;

    @GetMapping("/events")
    @ResponseBody
    public List<EventDto> getEvents(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @ModelAttribute LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @ModelAttribute LocalDateTime end
            , @RequestParam("_") String reqId) {

        List<EventDto> events = new ArrayList<>();
        events.add(new EventDto(2L, LocalDateTime.now().withHour(10).withMinute(0),
                LocalDateTime.now().withHour(16).withMinute(0),
                "33 ", "2"));

        // log.debug("Got {} {} - "+reqId, start, end);

        return events;
    }


    @GetMapping("/resources")
    public List<ResourceDto> getResources(@RequestParam("_") String reqId) {
        List<ResourceDto> calendars = new ArrayList<>();
        calendars.add(new ResourceDto("2", "dva"));
        calendars.add(new ResourceDto("3", "tri"));
        return calendars;
    }


    @PostMapping("/resources")
    public void saveResource(@RequestBody NewResourceDto param) {
        log.debug("dobio " + param);
    }

    @Data
    static class NewResourceDto {
        String name;
    }

}


