import java.util.LinkedList;

public class StableMarriageProblem {

	/*
	 * Solves the Stable Marriage Problem where there are an equal number of men and women
	 * Each man is paired with exactly one woman and everyone ranks everyone else from highest to lowest
	 * @param menPref is the list of men's preference lists
	 * @param womenPref is the list of women's preference lists
	 * @param num is the number of men being matched
	 * @result is the matching where the array's index value is the woman the man is matched to
	 */
	public static int[] stableMarriageProblem(int[][] menPref, int[][] womenPref, int num){
		int[] matching = new int[num];
		
		// The list of men who are not yet engaged
		LinkedList<Integer> freeMen = new LinkedList<Integer>();
		// The next woman each man can propose to
		int[] nextWomen = new int[num];
		// The man each woman is currently engaged to
		int[] womenPartner = new int[num];
		for(int i = 0; i < num; i++){
			freeMen.add(i);
			nextWomen[i] = 0;
			womenPartner[i] = -1;
		}
		
		// The ranking each woman gives each man
		int[][] ranking = new int[num][num];
		for(int i = 0; i < num; i++){
			for(int j = 0; j < num; j++){
				ranking[i][womenPref[i][j]] = j;
			}
		}
		
		while(!freeMen.isEmpty()){
			// The next man proposing
			int currentMan = freeMen.getFirst();
			// The woman he is proposing to
			int currentWoman = nextWomen[currentMan];
			nextWomen[currentMan]++;
			
			// If the woman is not currently engaged
			if(womenPartner[currentWoman] == -1){
				freeMen.removeFirst();
				womenPartner[currentWoman] = currentMan;
			}
			else {
				int otherMan = womenPartner[currentWoman];
				// If the woman ranks the current man higher than the other man, she becomes engaged to him
				if(ranking[currentWoman][otherMan] > ranking[currentWoman][currentMan]){
					freeMen.removeFirst();
					womenPartner[currentWoman] = currentMan;
					freeMen.add(otherMan);
				}
			}
		}
		
		// The engagements become final
		for(int i = 0; i < num; i++){
			matching[womenPartner[i]] = i;
		}
		
		return matching;
	}
	
}
