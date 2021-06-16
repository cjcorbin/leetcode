package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List letterCombination(String digits)
    {
        ArrayList<String> result = new ArrayList<>();

        if (digits != null && digits.length() > 0)
        {
            String map[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
            dfs(digits, map, result, new StringBuilder(), 0);
        }
        return result;
    }

    private void dfs(String digits, String[] map, ArrayList<String> result, StringBuilder sb, int index)
    {
        if (index == digits.length())
        {
            result.add(sb.toString());
            return;
        }

        int digit = Character.getNumericValue(digits.charAt(index));
        String letters = map[digit];

        for (int i = 0;i < letters.length();i++)
        {
            char ch = letters.charAt(i);
            sb.append(ch);
            dfs(digits, map, result, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //Subsets problem

    public List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, ArrayList<Integer> subset, int index)
    {
        res.add(new ArrayList<Integer>(subset));

        for (int i = index;i < nums.length;i++)
        {
            subset.add(nums[i]);
            dfs(nums, res, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

    //Max product

    public int maxProduct(int[] nums)
    {
        int maxProduct = -101;

        dfs(nums, maxProduct, 1,0, 1);

        return maxProduct;
    }

    public void dfs(int[] nums, int maxProduct, int currProduct, int start, int index)
    {
        if (currProduct > maxProduct && index - start > 1)
        {
            maxProduct = currProduct;
        }

        for (int i = start;i < nums.length;i++)
        {
            currProduct = currProduct * nums[i];
            dfs(nums, maxProduct, currProduct, start, i + 1);
        }
    }

    //Surrounded Regions

    public void surround(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        if (board.length < 2 || board[0].length < 2)
            return;
        int m = board.length, n = board[0].length;
        //Any 'O' connected to a boundary can't be turned to 'X', so ...
        //Start from first and last column, turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                boundaryDFS(board, i, 0);
            if (board[i][n-1] == 'O')
                boundaryDFS(board, i, n-1);
        }
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                boundaryDFS(board, 0, j);
            if (board[m-1][j] == 'O')
                boundaryDFS(board, m-1, j);
        }
        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
    }
    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j <0 || j > board[0].length - 1)
            return;
        if (board[i][j] == 'O')
            board[i][j] = '*';
        if (i > 1 && board[i-1][j] == 'O')
            boundaryDFS(board, i-1, j);
        if (i < board.length - 2 && board[i+1][j] == 'O')
            boundaryDFS(board, i+1, j);
        if (j > 1 && board[i][j-1] == 'O')
            boundaryDFS(board, i, j-1);
        if (j < board[i].length - 2 && board[i][j+1] == 'O' )
            boundaryDFS(board, i, j+1);
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if(colIndex == board.length) {
            res.add(construct(board));
            return;
        }

        for(int i = 0; i < board.length; i++) {
            if(validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                    return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

}


public class Main {

    public static void main(String[] args) {

        Solution sol = new Solution();

        //System.out.println(sol.letterCombination("23"));

        //System.out.println(sol.subsets(new int[]{1,2,3}));


//        char [][] cord = new char[][]{{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},
//                {'O','X','O','O','O'},{'X','X','O','X','O'}};
//
//        sol.surround(cord);

        sol.solveNQueens(4);








	// write your code here
    }
}
