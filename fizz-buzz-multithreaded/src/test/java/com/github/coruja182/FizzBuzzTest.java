package com.github.coruja182;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.IntConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@DisplayName("The FizzBuzz Test")
@ExtendWith(MockitoExtension.class)
public class FizzBuzzTest {

    @Captor
    ArgumentCaptor<String> messageCaptor;

    final Vector<String> vector = Mockito.mock(Vector.class);
    final Runnable fizzRunner = () -> vector.add("Fizz");
    final Runnable buzzRunner = () -> vector.add("Buzz");
    final Runnable fizzBuzzRunner = () -> vector.add("FizzBuzz");
    final IntConsumer numberPrinter = (int number) -> vector.add(String.valueOf(number));

    /**
     * Retrieves the values that FizzBuss should print according to the input integer.
     * <p>
     * "FizzBuzz" if number is divisible by 3 and 5,
     * "Fizz" if number is divisible by 3 and not 5,
     * "Buzz" if number is divisible by 5 and not 3,
     * or the number if number is not divisible by 3 or 5.
     */
    private String getExpectedOutput(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("0 should never be passed as parameter, as token is 1-indexed)");
        }
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    @Test
    @DisplayName("WHEN helper method is used it SHOULD return all the correct values")
    void helperMethodShouldEvaluateCorrectOutput() {
        String[] expectedValues = {"1",
                "2",
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz"};
        List<String> values = new ArrayList<>();
        for (int value = 0; value < expectedValues.length; value++) {
            values.add(getExpectedOutput(value + 1));
        }
        assertThat(values).containsExactly(expectedValues);
    }

    @Test
    @DisplayName("SHOULD print [1,2,fizz,4,buzz] WHEN input is 5")
    void shouldNotPrintFizzBuzzWhenInputBelow15() throws InterruptedException {
        // preparation of test
        int expectedNumberOfValues = 5;
        String[] expectedValuesPrinted = new String[expectedNumberOfValues];
        for (int value = 1; value <= expectedNumberOfValues; value++) {
            expectedValuesPrinted[value - 1] = getExpectedOutput(value);
        }


        // execution
        FizzBuzz subject = new FizzBuzz(expectedNumberOfValues);

        Thread threadA = new Thread(() -> {
            try {
                subject.fizz(fizzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            try {
                subject.buzz(buzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadB");

        Thread threadC = new Thread(() -> {
            try {
                subject.fizzbuzz(fizzBuzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadC");

        Thread threadD = new Thread(() -> {
            try {
                subject.number(numberPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadD");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        // this will us some time for the code to run and assertions to pass
        Thread.sleep(500);

        // assertions
        verify(vector, times(expectedNumberOfValues)).add(messageCaptor.capture());
        List<String> allValues = messageCaptor.getAllValues();
        assertThat(allValues).containsExactly(expectedValuesPrinted);
    }

    @Test
    @DisplayName("SHOULD print correct all values up and one FizzBuzz when a 15 is provided")
    void shouldPrintOneFizBuzzWhen15IsProvided() throws InterruptedException {

        // preparation of test
        int expectedNumberOfValues = 15;
        String[] expectedValuesPrinted = new String[expectedNumberOfValues];
        for (int value = 1; value <= expectedNumberOfValues; value++) {
            expectedValuesPrinted[value - 1] = getExpectedOutput(value);
        }

        FizzBuzz subject = new FizzBuzz(expectedNumberOfValues);

        Thread threadA = new Thread(() -> {
            try {
                subject.fizz(fizzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            try {
                subject.buzz(buzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadB");

        Thread threadC = new Thread(() -> {
            try {
                subject.fizzbuzz(fizzBuzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadC");

        Thread threadD = new Thread(() -> {
            try {
                subject.number(numberPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadD");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        // this will us some time for the code to run and assertions to pass
        Thread.sleep(500);

        // assertions
        verify(vector, times(expectedNumberOfValues)).add(messageCaptor.capture());
        List<String> allValues = messageCaptor.getAllValues();
        assertThat(allValues).containsExactly(expectedValuesPrinted);
    }

    @Test
    @DisplayName("SHOULD print only numbers when number below 3")
    void shouldPrintOnlyNumbersWhenInputIs2() throws InterruptedException {
        // preparation of test
        int expectedNumberOfValues = 2;
        String[] expectedValuesPrinted = new String[expectedNumberOfValues];
        for (int value = 1; value <= expectedNumberOfValues; value++) {
            expectedValuesPrinted[value - 1] = getExpectedOutput(value);
        }


        // execution
        FizzBuzz subject = new FizzBuzz(expectedNumberOfValues);

        Thread threadA = new Thread(() -> {
            try {
                subject.fizz(fizzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            try {
                subject.buzz(buzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadB");

        Thread threadC = new Thread(() -> {
            try {
                subject.fizzbuzz(fizzBuzzRunner);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadC");

        Thread threadD = new Thread(() -> {
            try {
                subject.number(numberPrinter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadD");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        // this will us some time for the code to run and assertions to pass
        Thread.sleep(500);

        // assertions
        verify(vector, times(expectedNumberOfValues)).add(messageCaptor.capture());
        List<String> allValues = messageCaptor.getAllValues();
        assertThat(allValues).containsExactly(expectedValuesPrinted);
    }
}
