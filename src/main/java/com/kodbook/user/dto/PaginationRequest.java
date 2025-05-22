package com.kodbook.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Pagination request parameters", implementation = PaginationRequest.class)
public class PaginationRequest {

    @Min(value = 0, message = "Page number must be 0 or greater")
    @Schema(description = "Page number (0-based)", example = "0", minimum = "0", defaultValue = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int pageNumber = 0;

    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 100, message = "Page size must not exceed 100")
    @Schema(description = "Number of items per page", example = "10", minimum = "1", maximum = "100", defaultValue = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int pageSize = 10;


    @Pattern(regexp = "ASC|DESC", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Sort direction must be either 'ASC' or 'DESC' (case insensitive)")
    @Schema(description = "Sort direction", example = "ASC", allowableValues = {"ASC", "DESC"}, defaultValue = "ASC", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sortDirection = "ASC";

    @Size(min = 1, message = "At least one sort property must be specified")
    @Schema(description = "Properties to sort by", example = "id", defaultValue = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String[] sortBy = {"id"};
}
