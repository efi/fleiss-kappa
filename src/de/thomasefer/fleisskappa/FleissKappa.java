package de.thomasefer.fleisskappa;

public class FleissKappa {

	static double calculate(long[][] lines) {
		if (lines==null) throw new IllegalArgumentException( "Non-null input expected." );
		int lineCount = lines.length;
		if (lineCount == 0) throw new IllegalArgumentException( "Empty input provided." );
		int columnCount = lines[0].length;
		if (columnCount == 0) throw new IllegalArgumentException( "Illegal number of columns provided." );
		boolean firstLine = true;
		long[] columnSums = new long[columnCount];
		double[] lineConformities = new double[lineCount];
		long lineSum=0;
		int lineNumber=0;
		for (long[] line : lines){
			if (line.length != columnCount) throw new IllegalArgumentException( "Constant number of columns expected." );
			int cellNumber=0;
			long currentLineSum=0;
			long currentLineSquaresSum = 0;
			for (long cell : line) {
				columnSums[cellNumber++] += cell;
				currentLineSquaresSum += cell*cell;
				currentLineSum += cell;
			}
			lineConformities[lineNumber++] = 1d/(currentLineSum*(currentLineSum-1))*(currentLineSquaresSum-currentLineSum);
			if (firstLine) {
				lineSum=currentLineSum;
			} else {
				if (lineSum != currentLineSum) throw new IllegalArgumentException( "Constant number of votes expected. (As "+lineSum+" in first line)" ) ;
			}
			firstLine=false;
		}
		if (columnCount==1 || lineSum==1) return 1.0;
		double columnWeightSquareSum=0;
		for (long columnSum : columnSums) columnWeightSquareSum += Math.pow(((double)columnSum)/lineSum/lineCount,2);
		double averageConformity=0;
		for (double lineConformity : lineConformities) averageConformity += lineConformity/lineNumber;
		return (averageConformity-columnWeightSquareSum)/(1.0-columnWeightSquareSum);
	}
	static double calculate(int[][] lines) {
		if (lines==null) throw new IllegalArgumentException( "Non-null input expected." );
		int lineCount = lines.length;
		if (lineCount == 0) throw new IllegalArgumentException( "Empty input provided." );
		int columnCount = lines[0].length;
		if (columnCount == 0) throw new IllegalArgumentException( "Illegal number of columns provided." );
		boolean firstLine = true;
		long[] columnSums = new long[columnCount];
		double[] lineConformities = new double[lineCount];
		long lineSum=0;
		int lineNumber=0;
		for (int[] line : lines){
			if (line.length != columnCount) throw new IllegalArgumentException( "Constant number of columns expected." );
			int cellNumber=0;
			long currentLineSum=0;
			long currentLineSquaresSum = 0;
			for (int cell : line) {
				columnSums[cellNumber++] += cell;
				currentLineSquaresSum += cell*cell;
				currentLineSum += cell;
			}
			lineConformities[lineNumber++] = 1d/(currentLineSum*(currentLineSum-1))*(currentLineSquaresSum-currentLineSum);
			if (firstLine) {
				lineSum=currentLineSum;
			} else {
				if (lineSum != currentLineSum) throw new IllegalArgumentException( "Constant number of votes expected. (As "+lineSum+" in first line)" ) ;
			}
			firstLine=false;
		}
		if (columnCount==1 || lineSum==1) return 1.0;
		double columnWeightSquareSum=0;
		for (long columnSum : columnSums) columnWeightSquareSum += Math.pow(((double)columnSum)/lineSum/lineCount,2);
		double averageConformity=0;
		for (double lineConformity : lineConformities) averageConformity += lineConformity/lineNumber;
		return (averageConformity-columnWeightSquareSum)/(1.0-columnWeightSquareSum);
	}
	
}
