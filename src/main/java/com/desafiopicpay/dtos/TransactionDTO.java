package com.desafiopicpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long sanderId, Long receiverId) {

}
