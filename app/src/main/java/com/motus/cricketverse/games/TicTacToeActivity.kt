package com.motus.cricketverse.games

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.motus.cricketverse.R

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var gridLayout: GridLayout
    private var board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        statusText = findViewById(R.id.statusText)
        gridLayout = findViewById(R.id.gridLayout)

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            val row = i / 3
            val col = i % 3
            button.setOnClickListener { onCellClicked(button, row, col) }
        }
        updateStatus()
    }

    private fun onCellClicked(button: Button, row: Int, col: Int) {
        if (board[row][col].isNotEmpty()) return
        board[row][col] = currentPlayer
        button.text = currentPlayer
        if (checkWin()) {
            statusText.text = getString(R.string.winner_text, currentPlayer)
            disableBoard()
        } else if (isBoardFull()) {
            statusText.text = getString(R.string.draw_text)
        } else {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
            updateStatus()
        }
    }

    private fun checkWin(): Boolean {
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true
        return false
    }

    private fun isBoardFull(): Boolean {
        for (row in board) {
            for (cell in row) if (cell.isEmpty()) return false
        }
        return true
    }

    private fun disableBoard() {
        for (i in 0 until gridLayout.childCount) {
            gridLayout.getChildAt(i).isEnabled = false
        }
    }

    private fun updateStatus() {
        statusText.text = getString(R.string.turn_text, currentPlayer)
    }
}

