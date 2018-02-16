// HarmonyState holds an array of Scales.
// calling play on harmony state causes it to update it's current chord in time with a clock, to be accessed by patterns
// To instatiate -> HarmonyState.new(scalesArray, durationsArray);
// uses method for get and method_ for set

// .play
// .stop

// .scales_(array) 
// .durations_(array)

HarmonyState { 
	var scales, durations, roots, currentScale, currentRoot, currentDuration, quant = 4, routine;
    *new { arg scalesArray, rootsArray, durationsArray;
        ^super.new.init(scalesArray, rootsArray, durationsArray);
    }
    init { arg scalesArray, rootsArray, durationsArray;
        scales = scalesArray;
        durations = durationsArray;
        roots = rootsArray;
        currentScale = scalesArray[0];
        currentRoot = rootsArray[0];
        currentDuration = durationsArray[0];
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
    currentRoot {
        ^currentRoot;
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
            loop {
                scales.do({|item, i| currentScale = item; currentRoot = roots[i]; durations[i].yield });
            }
    	}.play(quant: quant);
    }
    stop {
    	routine.stop;
    }
    doesNotUnderstand { arg selector...args;
        (this.class++" does not understand method "++selector);
    }
}