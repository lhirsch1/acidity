import kotlin.random.Random


//OUTPUT TO TRACKER

fun main(args: Array<String>) {

    var multiplier = 1
    var scale = intArrayOf(120,128,136, 0,1)
    var pitchSequence = pitchSequencer(multiplier, scale)


    var lfoRange = intArrayOf(0,100)
    var cutoffSequence = lfo1(4, 0, lfoRange)
    var track = buildTrack(pitchSequence, cutoffSequence)
    println(track.size)
}


fun pitchSequencer (multiplier: Int, scale: IntArray ): IntArray {

    var scale = scale
    var pitchSequence = intArrayRandomizer(16, scale)
    println("pitch size " + pitchSequence.size)

    var pitchSequenceAdjusted = intArrayOf()

    for (i in 0 until 16 step 1){
        pitchSequenceAdjusted += pitchSequence[i]
        pitchSequenceAdjusted += 300
        pitchSequenceAdjusted += 300
        pitchSequenceAdjusted += 300

        println(i.toString() + " len " + pitchSequenceAdjusted.size )
    }

    println("pitch array " + pitchSequenceAdjusted.contentToString())
    println(pitchSequenceAdjusted.size)
    return pitchSequenceAdjusted
}

fun intArrayRandomizer(size: Int, set: IntArray) : IntArray{
    var size = size
    var set = set

    var randomizedArray = intArrayOf()

    for (i in 0 until size step 1){

        var randomIndex = (0 until (set.size - 1)).random()
        randomizedArray += set[randomIndex]

    }

    return randomizedArray
}
fun filterSequence () {

    return

}

fun velocitySequence () {

}

fun lfo1 (multiplier: Int, startPoint: Int, minMax: IntArray): IntArray {
    //todo needs work

    var minMax = minMax

    // for 64 steps calculate stops on triangle wave start at min
    //range is number of completed waves

    var time = 64/multiplier
    var level = startPoint
    var range = minMax[1] - minMax[0]

    var stepIncrease = range/time

    var lfoArray = intArrayOf()
    var waveUp = true

    for (i in 1 until 63 step 1){
        var dataPoint = minMax[0]
        if (waveUp && (level + stepIncrease) > minMax[1]){
            level -= ((level + stepIncrease) - minMax[1])
            waveUp = false
            lfoArray += level
        }


        if (waveUp && (level + stepIncrease) >= minMax[0] && (level + stepIncrease) <= minMax[1]){
            level += stepIncrease
            lfoArray += level
            i +1
        }

//
        if (!waveUp &&(level + stepIncrease) < minMax[0]) {
            level += ((level - stepIncrease) - minMax[0]) * -1
            waveUp = true
            lfoArray += level
        }

        if (!waveUp && (level + stepIncrease) >= minMax[0] && (level + stepIncrease) <= minMax[1]){
            level -= stepIncrease
            lfoArray += level

        }
    }
    return lfoArray
}

fun buildTrack (pitchArray: IntArray, cutoffArray: IntArray): ArrayList<IntArray> {
    // make array of arrays ofvalues for each stuff
    var steps = 64
    var track = arrayListOf<IntArray>()

    for (i in 0 until 64 step 1) {
        var step = intArrayOf(pitchArray[i], cutoffArray[i])
        println(step.contentToString())
        track += step

    }
    return track
}