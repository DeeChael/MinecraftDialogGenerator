# Minecraft Dialog Generator
## Example Usage
```kotlin
confirmationDialog {
    title {
        text("Test Confirmation Dialog") with gold()
    }
    externalTitle {
        text("Enter ConfirmationDialog test") with blue()
    }
    body {
        plainMessage {
            contents {
                text("First line")
                newline()
                text("Second")
            }
        }
    }
    yes {
        label {
            text("click me to say yes") with green()
        }
        onClick(runCommand("/say I clicked yes"))
    }
    no {
        label {
            text("click me to say no") with red()
        }
        onClick(runCommand("/say I clicked no"))
    }
}
```