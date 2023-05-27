package com.application.socialhub.dto;

import com.application.socialhub.model.UserEntity;
import java.sql.Blob;
public record FriendsListResponse(UserEntity user,
                                  Blob profilePhoto) {
}
