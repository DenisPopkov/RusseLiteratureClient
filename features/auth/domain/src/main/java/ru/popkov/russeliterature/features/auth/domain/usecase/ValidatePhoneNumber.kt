package ru.popkov.russeliterature.features.auth.domain.usecase

import ru.popkov.russeliterature.features.auth.domain.model.ValidationResult
import javax.inject.Inject

private val PHONE_NUMBER_REGULAR = "(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))".toRegex()

class ValidatePhoneNumber @Inject constructor() {

    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Номер телефона не может быть пустым"
            )
        }

        if (!PHONE_NUMBER_REGULAR.matches(phoneNumber)) {
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