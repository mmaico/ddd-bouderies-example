package com.crm.infrastructure.helpers;

import com.crm.infrastructure.exceptions.ValidationException;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class HandlerErrorsTest {

    @Rule
    public ExpectedException throwing = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWithErrorsInContent() {
        Set<String> errors = new HashSet<String>();
        errors.add("errors.error.one");
        errors.add("errors.error.two");
        Boolean result = Boolean.FALSE;

        try {
            HandlerErrors.hasErrors(errors).throwing(ValidationException.class);
        } catch(ValidationException e) {
            result = e.getErrors().equals(errors);
        }

        MatcherAssert.assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldNotThrowExceptionWhenListErrorsIsEmpty() {
        Set<String> errors = new HashSet<String>();

        HandlerErrors.hasErrors(errors).throwing(ValidationException.class);
    }

    @Test
    public void shouldNotThrowExceptionWhenListIsNull() {
        Set<String> errors = null;

        HandlerErrors.hasErrors(errors).throwing(ValidationException.class);
    }

}
