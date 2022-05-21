import processing.core.PApplet;

public class Sketch extends PApplet {
	
  int intCellWidth = 20;
  int intCellHeight = 20;
  int intMargin = 5;
  int intRowCount = 10;
  int intColumnCount = 10;
  int intScreenWidth = (intRowCount * intCellWidth) + ((intRowCount + 1) * intMargin);
  int intScreenLength = (intColumnCount*intCellHeight) + ((intColumnCount + 1) * intMargin);

  int totalCellSelected = 0;
  
  int[][] intGrid = new int[intRowCount][intColumnCount];
  public void settings() {
    
    size(intScreenWidth, intScreenLength);
  }

  public void setup() {
    background(0);
  }

  
  public void draw() {
	  for(int column = 0; column < intColumnCount; column++){
      for(int row = 0; row < intRowCount; row++){
        if (intGrid[row][column]  == 1){
          fill(0,255,0);
          rect(intMargin * (column+1) + (intCellWidth * column), intMargin + (intMargin + intCellHeight) * row, intCellWidth, intCellHeight);  
        }
        else{
          fill(255);
          rect(intMargin + (column * (intCellWidth + intMargin)), intMargin + (row * (intCellHeight + intMargin)), intCellWidth, intCellHeight);
        }
      }
    }
  }

  public void mousePressed(){
    for( int column = 0; column < intColumnCount; column ++ ){
      for(int row = 0; row < intRowCount; row ++){
        if(mouseX/(intCellWidth + intMargin) == column && mouseY/(intCellHeight + intMargin) ==  row){
          //block pressed
          if (intGrid[row][column] == 1) {
            intGrid[row][column] = 0;
            totalCellSelected--;
          }
          else if (intGrid[row][column] == 0) {
            intGrid[row][column] = 1;
            totalCellSelected++;
            
          }
          
          // block above block pressed
          if(row > 0 && intGrid[row -1][column] ==  1){
            intGrid[row-1][column] = 0;
            totalCellSelected--;
          }
          else if(row > 0 && intGrid[row -1][column] == 0 ){
            intGrid[row - 1][column] = 1;
            totalCellSelected++;
          }

          // block below block pressed
          if(row < 9 && intGrid[row + 1][column] == 1){
            intGrid[row + 1][column] = 0;
            totalCellSelected--;
          }
          else if (row < 9 && intGrid[row + 1][column]== 0 ){
            intGrid[row + 1][column] = 1;
            totalCellSelected++;
          }

          //block to the left of block pressed
          if(column > 0 && intGrid[row][column -1] == 1 ){
            intGrid[row][column - 1] = 0;
            totalCellSelected--;
          }
          else if(column > 0 && intGrid[row][column - 1] == 0 ){
            intGrid[row][column - 1] = 1;
            totalCellSelected++;
          }

          // block to the right of block presed
          if(column < 9 && intGrid[row][column + 1] == 0 ){
            intGrid[row][column + 1] = 1;
            totalCellSelected++;
          }
          else if(column < 9 && intGrid[row][column + 1] == 1){
            intGrid[row][column + 1] = 0;
            totalCellSelected--;
          }
          System.out.println("total of " + totalCellSelected + " cells selected");
        }
    }
  }
  for (int row = 0; row < intRowCount; row++) {

    int rowCounter = 0;
    int intCurrentContinuous = 0;
    int intMaximum = 0;
    for (int column = 0; column < intColumnCount; column++) { 
     if (intGrid[row][column] == 1) {
        rowCounter++;
        intCurrentContinuous++;
        if (intCurrentContinuous > intMaximum) {
          intMaximum = intCurrentContinuous;
        }
      }
      else {
        intCurrentContinuous = 0;
      }
    }

    if (intMaximum > 2) {
      System.out.println("There are " + intMaximum + " continuous blocks selected on row " + row  );
      
    }

    System.out.println("Row " + row + " has " + rowCounter + " cells selected");
  }

  for (int column = 0; column < intRowCount; column++) {
    int intColumnCounter = 0;
    for (int row = 0; row < intColumnCount; row++) {
      if (intGrid[row][column] == 1) {
        intColumnCounter++;
      }
    }
    System.out.println("Column " + column + " has " + intColumnCounter + " cells selected");
  }
}
}
