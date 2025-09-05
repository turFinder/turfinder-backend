package net.tufinder.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto{
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserCreateDto {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserUpdateDto {
        private Long id;
        private String nidNo;
        private String phoneNo;
        private String address;
        private  String profileImageLink;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLoginDto {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoogleUserCreateDto {
        private String name;
        private String email;
        private String providerId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRetrieveDto {
        private Long id;
        private String name;
        private String email;
        private String token;
    }


}

