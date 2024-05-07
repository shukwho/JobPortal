package com.who.shuk.JobsMS.job.clients;

import com.who.shuk.JobsMS.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CompanyMS")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
