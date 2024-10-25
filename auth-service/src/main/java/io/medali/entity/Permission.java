package io.medali.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    SUPERADMIN_READ("superadmin:read"),
    SUPERADMIN_UPDATE("superadmin:update"),
    SUPERADMIN_CREATE("superadmin:create"),
    SUPERADMIN_DELETE("superadmin:delete"),
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_CREATE("student:create"),
    STUDENT_DELETE("student:delete"),
    TEACHER_READ("teacher:read"),
    TEACHER_UPDATE("teacher:update"),
    TEACHER_CREATE("teacher:create"),
    TEACHER_DELETE("teacher:delete"),
    HEADOFDEPARTEMENT_READ("headofdepartement:read"),
    HEADOFDEPARTEMENT_UPDATE("headofdepartement:update"),
    HEADOFDEPARTEMENT_CREATE("headofdepartement:create"),
    HEADOFDEPARTEMENT_DELETE("headofdepartement:delete"),
    RECRUITER_READ("headofdepartement:read"),
    RECRUITER_UPDATE("headofdepartement:update"),
    RECRUITER_CREATE("headofdepartement:create"),
    RECRUITER_DELETE("headofdepartement:delete");

    @Getter
    private final String permission;
}
