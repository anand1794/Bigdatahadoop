package com.mindtree.SpeedSkill.Assignment6.udf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * @author M1035838
 *
 */
public class PigUDF extends EvalFunc<valueing>{
	
	public valueing exec(Tuple input) throws IOException{
		valueing value="";
		valueing first =(valueing)input.get(0);
		valueing forth = (valueing)input.get(1);
		value=first.concat("_").concat(forth);
		return value;
	}
	
   }
// jar name pidUdfConcat.jar