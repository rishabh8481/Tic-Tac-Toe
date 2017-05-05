
class MiniMaxer {

	//the difficulty of game can be changed here. DEPTH=2,3,4 means easy,normal,hard respectively 
	int DEPTH=2;
	
	int expandedNodes;
	int winner;
	boolean rows, columns, dia;
	int[] originState;
	final int UTIL=3000;

	//return the A-B-Search result, the action to be taken
	public int abSearch(int[] state){
		expandedNodes=0;
			
			int result = 1000;
			double resultValue = Double.NEGATIVE_INFINITY;
			double alpha = Double.NEGATIVE_INFINITY;
			double beta = Double.POSITIVE_INFINITY;
			for (int i =0;i<20;i++) {
			//	if(state[i]!=0) continue;
				if(state[i]==0){
				System.out.println("analysing"+i);
				double value = minValue(getResult(state, i,2),
						DEPTH,alpha, beta);
				if (value > resultValue) {
					result = i;
					resultValue = value;
				}
				alpha = Math.max(alpha, resultValue);
				System.out.println("absearch alpha = "+alpha);
			}}
			return result;
			
	}

	//return true if some one win or draw in the current state 
	public boolean isTerminal(int[] state){
		//check  columns
		System.out.println("check columns");

		columns = true;
		for(int i = 0;i<5;i++){
			if (state[i]!=0){
			for(int j=1; j<4;j++){
			columns = columns&&(state[i]==state[i+(5*j)]);
			//System.out.println("cols checked");
					}
			if (columns == true) {
				if(state[i]==1)winner=1;else winner =2;
				System.out.println("yes columns"+i);
				return true;}
			columns = true;
		}}
		columns= false;
		
		
		//check  rows
		System.out.println("check rows");

		rows=true;
		int [] r= {0,1,5,6,10,11,15,16};
		for(int i=0;i<8;i++){
			if (state[r[i]]!=0){
			for(int j=1; j<4;j++){
				rows=rows&&(state[r[i]]==state[r[i]+j]);
						}
				if (rows == true) {
				if(state[r[i]]==1)winner=1;else winner =2; 
				System.out.println("yes rows"+i+"winner="+winner);
				return true;}
				rows = true;
		}}
		rows = false;
		//check diagonal
		System.out.println("check dia");

		dia= true;
			for(int i = 0;i<2;i++){
				if (state[i]!=0){
				for(int j=1; j<4;j++){
				dia=dia&&(state[i]==state[i+(6*j)]);
						}
				if (dia == true) {
					if(state[i]==1)winner=1;else winner =2; 
					System.out.println("yes dia"+i);
					return true;};
				dia = true;
			}}
			for(int i = 15;i<17;i++){
				if (state[i]==0)continue;
				for(int j=1; j<4;j++){
				dia=dia&&(state[i]==state[i-(4*j)]);
						}
				if (dia == true) {
					if(state[i]==1)winner=1;else winner =2; 
					System.out.println("yes columns"+i);
					return true;}
				dia = true;
			}
		dia=false;
		//check draw
		System.out.println("check draw");

		for(int i=0;i<20;i++){
			if(state[i]==0) {
				System.out.println("not draw");
				return false;}
		}
		winner =0;
		System.out.println("draw");

		return true;
	}
	
	//return the win/loss utility
	public int getUtility(int[] state){
		System.out.println("getUtility");
		if(winner == 1)
		return -UTIL;
		else if(winner==0)return 0;
		else return UTIL;
	}
	
	//return the state after a action taken
	public int[] getResult(int[] s, int where ,int who){
		int state[] = new int [20];
		for(int i = 0;i<20;i++){state[i]=s[i];}
		state[where]= who;
		return state;
	}
	
	
	//return the score of the state when cutoff occur
	//the score is the sum of each tile in row&column&diagonal direction
	public double getScore(int[] s){
		double score = 0;
		int counter1=0;
		int counter2=0;
		//row score
		for(int i =0;i<20;i+=5){
			for(int j=0;j<5;j++){
				if(s[i+j]==1)counter1++;
				if(s[i+j]==2)counter1++;
			}
			if(s[i+2]==2||s[i+1]==2 ||s[i+3]==2)counter1=0;
			if(s[i+2]==1||s[i+1]==1 ||s[i+3]==1)counter2=0;
			score += 2^counter2- 2^counter1;
			counter1=0;
			counter2=0;
		}
		//column score
		for (int i = 0;i<5;i++){
			for(int j=0;j<4;j++){
				if(s[i+5*j]==1)counter1++;
				if(s[i+5*j]==2)counter2++;
			}
			if(counter1*counter2==0)score+=2^counter2-2^counter1;
			counter1=0;
			counter2=0;
		}
		//dia score
		for (int i = 0;i<2;i++){
			for(int j=0;j<4;j++){
				if(s[i+6*j]==1)counter1++;
				if(s[i+6*j]==2)counter2++;
			}
			if(counter1*counter2==0)score+=2^counter2-2^counter1;
			counter1=0;
			counter2=0;
		}
		for (int i = 15;i<17;i++){
			for(int j=0;j<4;j++){
				if(s[i-4*j]==1)counter1++;
				if(s[i-4*j]==2)counter2++;
			}
			if(counter1*counter2==0)score+=2^counter2-2^counter1;
			counter1=0;
			counter2=0;
		}
		
		System.out.println("Score:"+score);
		return score;
	}
	
	
	//maxValue in A-B-Search
	public double maxValue(int[] state,int d,double alpha, double beta) {
	expandedNodes++;
	if(d<0){return getScore(state);}

	
	System.out.println("max");
	if (isTerminal(state))
		return getUtility(state);
	double value = Double.NEGATIVE_INFINITY;
	for (int i =0;i<20;i++) {
		if(state[i]!=0) continue;
		value = Math.max(value, minValue( //
				getResult(state, i, 2),d-1, alpha, beta));
		if (value >= beta)
			return value;
		alpha = Math.max(alpha, value);
	}
	return value;
	}
	//minValue in A-B-Search
	public double minValue(int[] state,int d, double alpha, double beta) {
	expandedNodes++;
	System.out.println("min");

	if (isTerminal(state))
		return getUtility(state);
	if(d<0){return getScore(state);}
	
	double value = Double.POSITIVE_INFINITY;
	for (int i =0;i<20;i++) {
		if(state[i]!=0) continue;
		value = Math.min(value, maxValue( //
				getResult(state, i, 1),d-1, alpha, beta));
		if (value <= alpha)
			return value;
		beta = Math.min(beta, value);
	}
	return value;
	}
}
