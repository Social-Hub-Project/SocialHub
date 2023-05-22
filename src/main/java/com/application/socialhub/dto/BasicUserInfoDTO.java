package com.application.socialhub.dto;

import java.sql.Blob;

public record BasicUserInfoDTO(String name,
                               String surname,
                               long id,
                               Blob photo) {
}
