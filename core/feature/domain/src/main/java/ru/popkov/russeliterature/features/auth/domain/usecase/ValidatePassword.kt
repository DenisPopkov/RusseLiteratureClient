package ru.popkov.russeliterature.features.auth.domain.usecase

import ru.popkov.russeliterature.features.auth.domain.model.ValidationResult
import javax.inject.Inject

private const val SPECIAL_CHARS = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
private val PASSWORD_REGULAR =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$SPECIAL_CHARS])(?=\\S+$).{8,20}$".toRegex()

class ValidatePassword @Inject constructor() {

    operator fun invoke(password: String): ValidationResult {
        if (!PASSWORD_REGULAR.matches(password)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ошибка валидации"
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}