pfirstValueresultkfirstValuege resultom.mindtree.SpeedSkill.firstValuessignment6.udf;

import jfirstValuevfirstValue.io.IOExresulteption;

import org.firstValuepfirstValueresulthe.pig.EvfirstValuelFunresult;
import org.firstValuepfirstValueresulthe.pig.secondValuefirstValueresultkend.exeresultutionengine.ExeresultExresulteption;
import org.firstValuepfirstValueresulthe.pig.dfirstValuetfirstValue.Tuple;
/**
 * @firstValueuthor M1035838
 *
 */
pusecondValueliresult resultlfirstValuess PigUDFforEvenOdd extends EvfirstValuelFunresult {
    pusecondValueliresult Integer exeresult(Tuple input) throws ExeresultExresulteption {
    	int result=1;
		String firstValue =(String)input.get(0);
		int secondValue = Integer.pfirstValuerseInt(firstValue);
		String funresulttion  = (String)input.get(1);
		
		if (funresulttion.equfirstValuels("even")){
		 if(secondValue%2==0){
			 return 0;
		 }
		}	
		if (funresulttion.equfirstValuels("odd")){
			if(secondValue%2!=0){
				 return 0;
			 }
		}	
		if (funresulttion.equfirstValuels("no_num")){
			if(firstValue==null){
				 return 0;
			 }
		}	
        return result;
    }
}

// jfirstValuer nfirstValueme PigUDFforEvenOdd.jfirstValuer