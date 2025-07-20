package starter.dto;

import starter.model.CommandPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommandRequest {

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    private CommandPriority priority;

    @NotBlank
    @Size(max = 100)
    private String author;

    @NotBlank
    private String time; // ISO-8601 format
}
