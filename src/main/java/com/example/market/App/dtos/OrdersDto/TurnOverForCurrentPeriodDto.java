package com.example.market.App.dtos.OrdersDto;

import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class TurnOverForCurrentPeriodDto {

    private LocalDate from;

    private LocalDate to;

    public TurnOverForCurrentPeriodDto() {
    }

    public TurnOverForCurrentPeriodDto(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
