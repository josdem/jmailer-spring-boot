package com.jos.dem.jmailer.service;

import com.jos.dem.jmailer.service.impl.FilterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
class FilterServiceTest {

    private final FilterService filterService = new FilterServiceImpl();

    @Test
    @DisplayName("filtering user")
    void shouldFilterUser(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        var keyword = "NHUQfuLarRMDj";
        assertFalse(filterService.isValidUser(keyword));
    }
}
