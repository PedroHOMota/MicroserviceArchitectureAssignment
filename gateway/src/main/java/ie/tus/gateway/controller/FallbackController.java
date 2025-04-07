/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2025
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package ie.tus.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport(){
        return Mono.just("An error has occurred. Please contact support");
    }

}

