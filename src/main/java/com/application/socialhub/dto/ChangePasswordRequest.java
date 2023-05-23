package com.application.socialhub.dto;

public record ChangePasswordRequest(String token,String newPassword,String newPasswordConfirm) {
}
