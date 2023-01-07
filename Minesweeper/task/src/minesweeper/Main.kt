package minesweeper

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

const val FIELD_SIZE = 9
const val MAX_NUM_OF_MINES = FIELD_SIZE * FIELD_SIZE

class Cell() {
    var isHidden: Boolean = true
    var value: Char = '/'
    var isMarked = false
    fun show() : Char = if (isHidden) if (isMarked) '*' else '.' else value
}

fun setMines(field: MutableList<MutableList<Cell>>, minesNum: Int, cellFreeOfMine: Pair<Int, Int>? = Pair(-1, -1)) : Set<Pair<Int, Int>> {
    val generator = Random.Default
    var minesSet = 0
    val minesCoordinates = mutableSetOf<Pair<Int, Int>>()
    while (minesSet < minesNum) {
        val i = generator.nextInt(0, FIELD_SIZE)
        val j = generator.nextInt(0, FIELD_SIZE)
        if (field[i][j].value != 'X' && cellFreeOfMine != Pair(i, j)) {
            field[i][j].value = 'X'
            minesCoordinates += Pair(i, j)
            minesSet++
        }
    }
    return minesCoordinates
}

fun incrementNumOfMinesAround(field: MutableList<MutableList<Cell>>, rowNum: Int, columnNum: Int): Any =
    if (field[rowNum][columnNum].value == '/') field[rowNum][columnNum].value = '1' else field[rowNum][columnNum].value++

fun defineHints(field: MutableList<MutableList<Cell>>) {
    for (i in 0 until FIELD_SIZE) {
        for (j in 0 until FIELD_SIZE) {
            if (field[i][j].value == 'X') {
                for (i1 in max(0, i - 1)..min(i + 1, FIELD_SIZE - 1)) {
                    for (j1 in max(0, j - 1)..min(j + 1, FIELD_SIZE - 1)) {
                        if (field[i1][j1].value != 'X') incrementNumOfMinesAround(field, i1, j1)
                    }
                }
            }
        }
    }
}

fun printField(field: MutableList<MutableList<Cell>>) {
    print("\n |")
    repeat(FIELD_SIZE) {
        print(it + 1)
    }
    print("|\n—|" + "—".repeat(FIELD_SIZE) + "|\n")
    for (i in field.indices) {
        var stringToOutput = ""
        for (j in field[i].indices) stringToOutput += field[i][j].show()
        println("${i + 1}|$stringToOutput|")
    }
    print("—|" + "—".repeat(FIELD_SIZE) + "|\n")
}

fun replaceMines(field: MutableList<MutableList<Cell>>, minesNum: Int, freeOfMinesCell: Pair<Int, Int>) : Set<Pair<Int, Int>> {
    for (i in 0 until FIELD_SIZE) {
        for (j in 0 until FIELD_SIZE) {
            field[i][j].value = '.'
        }
    }
    return setMines(field, minesNum, freeOfMinesCell)
}

fun freeUpCellArea(field: MutableList<MutableList<Cell>>, i: Int, j: Int) {
    if (field[i][j].value != 'X' && field[i][j].isHidden) {
        field[i][j].isHidden = false
        if (field[i][j].value == '/') {
            for (i1 in max(0, i - 1)..min(i + 1, FIELD_SIZE - 1)) {
                for (j1 in max(0, j - 1)..min(j + 1, FIELD_SIZE - 1)) {
                    freeUpCellArea(field, i1, j1)
                }
            }
        }
    }
}

fun freeUpMines(field: MutableList<MutableList<Cell>>) {
    for (i in 0 until FIELD_SIZE) {
        for (j in 0 until FIELD_SIZE) {
            if (field[i][j].value == 'X') {
                field[i][j].isHidden = false
            }
        }
    }
}

fun openCell(field: MutableList<MutableList<Cell>>, i: Int, j: Int) : Boolean {
    return if (field[i][j].value == 'X') false else {
        freeUpCellArea(field, i, j)
        true
    }
}

fun onlyMinesAreHidden(field: MutableList<MutableList<Cell>>, minesCoordinates: Set<Pair<Int, Int>>) : Boolean {
    val minesCoordinatesCopy = minesCoordinates.toMutableSet()
    for (i in 0 until FIELD_SIZE) {
        for (j in 0 until FIELD_SIZE) {
            if (field[i][j].isHidden) {
                if (field[i][j].value == 'X') {
                    minesCoordinatesCopy -= Pair(i, j)
                } else {
                    return false
                }
            }
        }
    }
    return minesCoordinatesCopy.isEmpty()
}

fun main() {
    var firstFreeCommandIsCalled = false
    print("How many mines do you want on the field? > ")
    var minesNum = readln().toInt()
    while (minesNum > MAX_NUM_OF_MINES) {
        print("Number of mines to set is bigger than number of fields, please reset it: > ")
        minesNum = readln().toInt()
    }
    val field = MutableList(FIELD_SIZE) { MutableList(FIELD_SIZE) { Cell() } }
    printField(field)
    var minesCoordinates = setMines(field, minesNum)
    defineHints(field)
    val markedCoordinates = mutableSetOf<Pair<Int, Int>>()
    var failed = false
    while (minesCoordinates != markedCoordinates && !failed && !onlyMinesAreHidden(field, minesCoordinates)) {
        print("Set/unset mines marks or claim a cell as free: ")
        val newCoordinates = readln().trim().split(" ")
        val j = newCoordinates[0].toInt() - 1
        val i = newCoordinates[1].toInt() - 1
        if (newCoordinates[2] == "free") {
            if (!firstFreeCommandIsCalled) {
                firstFreeCommandIsCalled = true
                if (markedCoordinates.contains(Pair(i, j))) {
                    minesCoordinates = replaceMines(field, minesNum, Pair(i, j))
                    defineHints(field)
                }
            }
            failed = !openCell(field, i, j)
            if (failed) freeUpMines(field)
        } else {
            field[i ][j].isMarked = !field[i][j].isMarked
            if (field[i][j].isMarked) markedCoordinates += Pair(i, j) else markedCoordinates -= Pair(i, j)
        }
        printField(field)
    }
    if (!failed) {
        print("Congratulations! You found all the mines!")
    } else {
        print("You stepped on a mine and failed!")
    }
}
