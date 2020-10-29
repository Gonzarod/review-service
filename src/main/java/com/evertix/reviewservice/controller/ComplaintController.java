package com.evertix.reviewservice.controller;


import com.evertix.reviewservice.entities.Complaint;
import com.evertix.reviewservice.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Complaint", description = "API")
@RestController
@RequestMapping("/api")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;


    @GetMapping("/complaints")
    @Operation(summary = "Get All Complaints", description = "Get Complaints", tags = {"Complaint"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<Complaint> getAllComplaints(@PageableDefault @Parameter(hidden = true) Pageable pageable){
        return complaintService.getAllComplaints(pageable);

    }
    /*

    @GetMapping("/madeByUsers/{madeById}/complaints")
    @Operation(summary = "Get All Complaints By MadeBy", description = "Get All Complaints By MadeBy", tags = {"Complaint"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<ComplaintResource> getAllComplaintsByMadeById(@PathVariable(name = "madeById") Long madeById, @PageableDefault @Parameter(hidden = true) Pageable pageable){
        Page<Complaint> complaintPage = complaintService.getAllComplaintsByMadeById(madeById, pageable);
        List<ComplaintResource> resources = complaintPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,complaintPage.getTotalElements());
    }

    @GetMapping("/reportedUsers/{reportedId}/complaints")
    @Operation(summary = "Get All Complaints By Reported", description = "Get All Complaints By Reported", tags = {"Complaint"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<ComplaintResource> getAllComplaintsByReportedId(@PathVariable(name = "reportedId") Long reportedId, @PageableDefault @Parameter(hidden = true) Pageable pageable){
        Page<Complaint> complaintPage = complaintService.getAllComplaintsByReportedId(reportedId, pageable);
        List<ComplaintResource> resources = complaintPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,complaintPage.getTotalElements());
    }

    @PostMapping("/madeBy/{madeById}/reported/{reportedId}/complaints")
    @Operation(summary = "Create Complaint", description = "Create Complaint", tags = {"Complaint"})
    public ComplaintResource createComplaint(@PathVariable(name = "madeById") Long madeById,
                                                 @PathVariable(name = "reportedId") Long reportedId,
                                                 @Valid @RequestBody ComplaintSaveResource resource){
        return convertToResource(complaintService.createComplaint(madeById,reportedId,convertToEntity(resource)));
    }

    @PutMapping("complaints/{complaintId}")
    @Operation(summary = "Update Complaint", description = "Update Complaint", tags = {"Complaint"})
    public ComplaintResource updateComplaint(@PathVariable(name = "complaintId") Long complaintId,
                                                 @Valid @RequestBody ComplaintSaveResource resource){
        return convertToResource(complaintService.updateComplaint(complaintId,convertToEntity(resource)));
    }

    @DeleteMapping("complaints/{complaintId}")
    @Operation(summary = "Delete Complaint", description = "Delete Complaint", tags = {"Complaint"})
    public ResponseEntity<?> deleteComplaint(@PathVariable(name = "complaintId") Long complaintId){
        return complaintService.deleteComplaint(complaintId);
    }

     */

}
