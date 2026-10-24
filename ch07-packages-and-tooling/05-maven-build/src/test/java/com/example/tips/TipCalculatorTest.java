package com.example.tips;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TipCalculatorTest {

    @Test
    void fifteenPercentOfFortyIsSix() {
        assertEquals(6.00, TipCalculator.tip(40.00, 15), 0.001);
    }

    @Test
    void roundsToCents() {
        assertEquals(1.67, TipCalculator.tip(11.11, 15), 0.001);
    }

    @Test
    void rejectsNegativeBill() {
        assertThrows(IllegalArgumentException.class, () -> TipCalculator.tip(-1, 10));
    }
}
