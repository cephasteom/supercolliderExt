// HarmonyState holds an array of Scales.
// calling play on harmony state causes it to update it's current chord in time with a clock, to be accessed by patterns
// To instatiate -> HarmonyState.new(scalesArray, durationsArray);
// uses method for get and method_ for set

// .play
// .stop

// .scales_(array) 
// .durations_(array)

HarmonyState { 
	var scales, durations, currentScale, quant = 4, routine;
    *new { arg scalesArray, durationsArray;
        ^super.new.init(scalesArray, durationsArray);
    }
    init { arg scalesArray, durationsArray;
        scales = scalesArray;
        durations = durationsArray;
        currentScale = scalesArray[0];
    }
    currentScale {
    	^currentScale;
    }
    allScales { 
        ^scales;
    }
    scales_ { arg array;
        scales = array;
    }
    durations { 
        ^durations;
    }
    durations_ { arg array;
        durations = array;
    }
    quant {
        ^quant;
    }
    quant_ { arg val;
        quant = val;
    }
    play {
    	routine = Routine {
    		scales.do({|item, i| currentScale = item; durations[i].yield });
    	}.play(quant: quant);
    }
    stop {
    	routine.stop;
    }
    doesNotUnderstand { arg selector...args;
        (this.class++" does not understand method "++selector);
    }
}