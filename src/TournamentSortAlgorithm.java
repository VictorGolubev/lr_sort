import java.util.ArrayList;
import java.util.List;

public class TournamentSortAlgorithm {

    public void sort(List<Integer> numbers, List<Integer> secondRun, List<Integer> secondRunList, List<Integer> sortedNumbers) {

        List<Integer> branchLeaves1 = new ArrayList<Integer>();
        List<Integer> branchLeaves2 = new ArrayList<Integer>();

        Integer topBranch1Number = null;
        Integer topBranch2Number = null;
        Integer topNumber = null;

        int addingElementsToBranchCounter = 2;


        do {

            addToBranch ( numbers, branchLeaves1, secondRunList, addingElementsToBranchCounter);
            addToBranch ( numbers, branchLeaves2, secondRunList, addingElementsToBranchCounter);
//			addNumber(branchLeaves1, numbers);
//			addNumber(branchLeaves2, numbers);
//

            while (topBranch1Number == null) {

                topBranch1Number = minNumber(branchLeaves1);

                if(numbers.size() != 0 || branchLeaves1.size() != 0) {

                    while (branchLeaves1.size() != 2) {

                        if (numbers.size() != 0 || secondRunList.size() != 0 ) {

                            addToBranch ( numbers, branchLeaves1, secondRunList, addingElementsToBranchCounter);

                            if (isLessThanLastSortedNumber(branchLeaves1, sortedNumbers)) {

                                addNumberToSecondRun (branchLeaves1, secondRun);

                            }

                            if (topBranch1Number != null) {

                                if(isLessThanTopBranchNumber ( branchLeaves1, topBranch1Number)) {

                                    Integer tempInt = branchLeaves1.get(1);
                                    branchLeaves1.remove(1);
                                    branchLeaves1.add(topBranch1Number);
                                    topBranch1Number = tempInt;

                                }

                            }

                        } else {

                            break;

                        }

                    }

                } else {

                    break;

                }


            }


            while (topBranch2Number == null) {

                topBranch2Number = minNumber(branchLeaves2);

                if(numbers.size() != 0 || branchLeaves2.size() != 0) {

                    while (branchLeaves2.size() != 2) {

                        if (numbers.size() != 0 || secondRunList.size() != 0) {

                            addToBranch ( numbers, branchLeaves2, secondRunList, addingElementsToBranchCounter);

                            if (isLessThanLastSortedNumber(branchLeaves2, sortedNumbers)) {

                                addNumberToSecondRun (branchLeaves2, secondRun);

                            }

                            if (topBranch2Number != null) {

                                if(isLessThanTopBranchNumber ( branchLeaves2, topBranch2Number)) {

                                    Integer tempInt = branchLeaves2.get(1);
                                    branchLeaves2.remove(1);
                                    branchLeaves2.add(topBranch2Number);
                                    topBranch2Number = tempInt;

                                }

                            }

                        } else {

                            break;

                        }

                    }
                } else {

                    break;

                }



//
//				if(numbers.size() != 0 || branchLeaves2.size() != 0) {
//
//					addNumber(branchLeaves2, numbers);
//
//					if (isLessThanLastSortedNumber(branchLeaves2, sortedNumbers)) {
//
//						addNumberToSecondRun (branchLeaves2, secondRun);
//
//					}
//
//					if (topBranch2Number != null) {
//
//						if(isLessThanTopBranchNumber ( branchLeaves2, topBranch2Number)) {
//
//							 swapTopAndBranchNumber (branchLeaves2, topBranch2Number);
//
//						}
//
//					}
//
//				} else {
//
//					break;
//
//				}


            }

            topNumber = getBranchTop(branchLeaves1, branchLeaves2, topBranch1Number, topBranch2Number);

            if (topNumber != null) {

                sortedNumbers.add(topNumber);

                if ( topNumber == topBranch1Number) {

                    //branchLeaves1.remove(branchLeaves1.indexOf(topBranch1Number));
                    topBranch1Number = null;
                } else {

                    //branchLeaves2.remove(branchLeaves2.indexOf(topBranch2Number));
                    topBranch2Number = null;

                }

                topNumber = null;

            }


        } while (numbers.size()!=0  || branchLeaves1.size() != 0 ||branchLeaves2.size() != 0 || topBranch1Number != null || topBranch2Number != null);

        //return sortedNumbers;

    }

    public Integer minNumber(List<Integer> branchLeaves) {

        Integer min = (Integer) null;

        if (branchLeaves.size() == 1) {

            min =  branchLeaves.get(0);
            branchLeaves.remove(0);

        } else if(branchLeaves.size() == 2) {

            min = (branchLeaves.get(0) < branchLeaves.get(1)? branchLeaves.get(0):branchLeaves.get(1));

            branchLeaves.remove(branchLeaves.indexOf(min));
        }

        return min;

    }

    public Integer getBranchTop(List<Integer> branchLeaves1, List<Integer> branchLeaves2, Integer topBranch1Number, Integer topBranch2Number) {
        Integer min = (Integer) null;

        if(topBranch1Number != null &  topBranch2Number != null) {

            min = (topBranch1Number < topBranch2Number?topBranch1Number:topBranch2Number);


        } else if (topBranch1Number != null) {

            min = topBranch1Number;
            //branchLeaves1.remove(branchLeaves1.indexOf(topBranch1Number));

        } else if (topBranch2Number != null) {

            min = topBranch2Number;
            //branchLeaves2.remove(branchLeaves2.indexOf(topBranch2Number));

        }

        return min;

    }

    public void addNumber(List<Integer> branchLeaves, List<Integer> listOfInputNumbers) {

        if (branchLeaves.size() < 2) {

            branchLeaves.add(listOfInputNumbers.get(0));
            listOfInputNumbers.remove(0);

        }


    }

    public boolean isLessThanTopBranchNumber ( List<Integer> branchLeaves, Integer topBranchNumber) {

        if (branchLeaves.size() == 2) {

            if ( topBranchNumber > branchLeaves.get(1)) {

                return true;

            } else {

                return false;

            }

        } else {
            return false;
        }


    }

    public boolean isLessThanLastSortedNumber (List<Integer> branchLeaves, List<Integer> sortedNumbers) {

        if (sortedNumbers.size() == 0) {

            return false;

        } else {

            if (branchLeaves.size() == 2) {

                if ( sortedNumbers.get(sortedNumbers.size() - 1) > branchLeaves.get(1)) {

                    return true;

                } else {

                    return false;

                }


            } else {

                return false;
            }



        }



    }

    public void swapTopAndBranchNumber (List<Integer> branchLeaves, Integer topBranchNumber) {

        Integer tempInt = branchLeaves.get(1);
        branchLeaves.remove(1);
        branchLeaves.add(topBranchNumber);
        topBranchNumber = tempInt;

    }

    public void addNumberToSecondRun( List<Integer> branchLeaves, List<Integer> secondRun) {

        secondRun.add(branchLeaves.get(1));
        branchLeaves.remove(1);

    }


    public void addToBranch (List<Integer> numbers, List<Integer> branchLeaves, List<Integer> secondRunList, int counter) {

        while (branchLeaves.size() < 2) {

            if (numbers.size() != 0 && secondRunList.size() != 0) {

                if (counter % 2 == 0 ) {

                    addNumber(branchLeaves, numbers);
                    counter ++;

                } else {

                    addNumber(branchLeaves, secondRunList);
                    counter ++;
                }

            } else if (numbers.size() != 0) {

                addNumber(branchLeaves, numbers);


            } else if (secondRunList.size() != 0) {
                addNumber(branchLeaves, secondRunList);

            } else {

                break;

            }

        }

    }

}