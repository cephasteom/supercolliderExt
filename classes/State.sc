// HarmonyState holds an array of Scales.
// calling play on harmony state causes it to update it's current chord in time with a clock, to be accessed by patterns
// To instatiate -> HarmonyState.new(scalesArray, durationsArray);

// .play
// .pause
// .stop

// .scales_(array)
// .durations_(array)

HarmonyState { 
	var scales, durations, currentScale, clock = TempoClock.default, quant = 4;
    *new { arg scalesArray, durationsArray;
        ^super.new.init(array)
    }
    currentScale {
    	^currentScale;
    }
    scales { 
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
    prop1_ { arg newValue;
    	prop1 = newValue;
    	^prop1;
    }
    play {
    	routine = Routine {
    		scales.do({|item, i| currentScale = item; durations[i].yield })	
    	}.play;
    }
    stop {
    	routine.stop
    }

    doesNotUnderstand { arg selector...args;
        (this.class++" does not understand method "++selector);

        If(UGen.findRespondingMethodFor(selector).notNil){
            "But UGen understands this method".postln
        };
    }

}