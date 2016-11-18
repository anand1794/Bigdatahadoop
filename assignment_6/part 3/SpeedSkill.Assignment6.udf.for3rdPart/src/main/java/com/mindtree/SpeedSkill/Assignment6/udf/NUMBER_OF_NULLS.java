package com.mindtree.SpeedSkill.Assignment6.udf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
/**
 * @author M1035838
 *
 */
public class NUMBER_OF_NULLS extends EvalFunc {
    public Integer exec(Tuple input) throws ExecException {
        if (input == null) { return 0; }

        int c = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == null) c++;
        }
        return c;
    }
}

// jar name Numerofnull.jar