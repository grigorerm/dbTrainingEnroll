package com.db.bex.dbTrainingEnroll.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class ManagerRequestDto {
    private String email;
    private Long id;
}
