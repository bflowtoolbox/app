import java.util.ArrayList;

public class Jen {

	public static void main (String [] args){
		ArrayList<ArrayList<Integer>> subclauses = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> c1 = new ArrayList<Integer>();
		ArrayList<Integer> c2 = new ArrayList<Integer>();
		ArrayList<Integer> c3 = new ArrayList<Integer>();

		c1.add(new Integer(1));
		c1.add(new Integer(2));

		c2.add(new Integer(3));
		c2.add(new Integer(4));
		c2.add(new Integer(5));

		c3.add(new Integer(6));
		c3.add(new Integer(7));

		subclauses.add(c1);
		subclauses.add(c2);
		subclauses.add(c3);

		Integer a = new Integer(-8);

		// a is literal 8
		// going to generate translation for the following axiom:
		// 8 => ((1 ^ 2) v (3 ^ 4 ^ 5) v (6 ^ 7))
		// a => ((b ^ c) v (d ^ e ^ f) v (g ^ h))

		// which should be:
		// (~a v b v d v g) ^ (~a v b v d v h) ^ (~a v b v e v g) ^ (~a v b v e v h) 
		// ^ (~a v b v f v g) ^ (~a v b v f v h) ^ (~a v c v d v g) ^ (~a v c v d v h) 
		// ^ (~a v c v e v g) ^ (~a v c v e v h) ^ (~a v c v f v g) ^ (~a v c v f v h)

		ArrayList<ArrayList<Integer>> comb = generateCombinations(subclauses, 0, new ArrayList<ArrayList<Integer>>());

		// adding ~a to all combinations
		for (int i = 0; i < comb.size(); i++){
			comb.get(i).add(0, a);
		}

		for (int i = 0; i < comb.size(); i++){
			System.out.println(comb.get(i));
		}
	}
	
	public static ArrayList<ArrayList<Integer>> generateCombinations (ArrayList<ArrayList<Integer>> subclauses, int index, ArrayList<ArrayList<Integer>> comb){

		// base case, no more subclauses
		if (index == subclauses.size())
			return comb;

		// get current subclause
		ArrayList<Integer> clause = subclauses.get(index);

		ArrayList<ArrayList<Integer>> newComb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmp;
		if (index > 0){
			// add all possible combinations to the set of clauses we've already built up
			for (int i = 0; i < clause.size(); i++){
				for (int j = 0; j < comb.size(); j++){
					// get combination j, add literal i from clause to it and store it in the combinations arraylist
					tmp = new ArrayList<Integer>(comb.get(j));
					tmp.add(clause.get(i));
					newComb.add(tmp);
				}
			}
		}
		else{
			// need to setup comb
			for (int i = 0; i < clause.size(); i++){
				tmp = new ArrayList<Integer>();
				tmp.add(clause.get(i));
				newComb.add(tmp);
			}
		}
		// newComb becomes comb, increment index
		return generateCombinations(subclauses, index+1, newComb);
	}
}



/*
for (index1 = 0; index1 < vTemp.size(); index1++) {			
	for (index2 = 0; index2 < vTemp.size(); index2++) {
		if (index1 != index2) {
			VecInt vi1 = vTemp.get(index1);
			VecInt vi2 = vTemp.get(index2);
					
			for (int index3 = 0; index3< vi1.size();index3++) {						
				for (int index4 = 0; index4 < vi2.size(); index4++) {
					vi = new VecInt();
					vi.push(tIndex + special * -1);
					vi.push(vi1.get(index3));
					vi.push(vi2.get(index4));
					vi.push(0);
					v.add(vi);
				}
			}
		
		}
	}
}
*/
