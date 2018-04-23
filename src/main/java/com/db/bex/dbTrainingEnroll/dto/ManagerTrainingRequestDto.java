package com.db.bex.dbTrainingEnroll.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class ManagerTrainingRequestDto {
    private String email;
    private Long id;
}
