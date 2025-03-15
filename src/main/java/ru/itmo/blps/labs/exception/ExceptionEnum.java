package ru.itmo.blps.labs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    BAD_REQUEST("Некорректный запрос", HttpStatus.BAD_REQUEST),
    AUTHORIZATION_ERROR("Ошибка авторизации", HttpStatus.UNAUTHORIZED),
    VALIDATION_EXCEPTION("Ошибка валидации данных", HttpStatus.BAD_REQUEST),
    NOT_FOUND("Запрашиваемый ресурс не найден", HttpStatus.NOT_FOUND),
    ANNOUNCEMENT_IS_NOT_IN_CREATED_OR_DRAFT_STATUS("Объявление не находится в статусе DRAFT или CREATED",
        HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("Пользователь не найден", HttpStatus.NOT_FOUND),
    INCORRECT_PHONE_NUMBER("Некорректный номер телефона", HttpStatus.BAD_REQUEST),
    INCORRECT_AUTH_CODE("Неверный код авторизации", HttpStatus.BAD_REQUEST),
    INVALID_JWT_TOKEN("Неверный токен", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("Токен устарел", HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_JWT("Неподдерживаемый токен", HttpStatus.UNAUTHORIZED),
    MALFORMED_JWT("Некорректный токен", HttpStatus.UNAUTHORIZED),
    INVALID_SIGNATURE("Неверная подпись", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD("Неверный пароль", HttpStatus.BAD_REQUEST),
    INVALID_DATE("Некорректная дата", HttpStatus.BAD_REQUEST),

    SERVER_ERROR("", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String error;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionEnum(String message, HttpStatus httpStatus) {
        this.error = name();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
