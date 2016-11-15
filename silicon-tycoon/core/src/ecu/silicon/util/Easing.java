package ecu.silicon.util;

public class Easing {

	public static float easeInQuad(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * (t /= d) * t + b;
	}

	public static float easeOutQuad(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return -c * (t /= d) * (t - 2) + b;
	}

	public static float easeInOutQuad(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return c / 2 * t * t + b;
		return -c / 2 * ((--t) * (t - 2) - 1) + b;
	}

	public static float easeInCubic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * (t /= d) * t * t + b;
	}

	public static float easeOutCubic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * ((t = t / d - 1) * t * t + 1) + b;
	}

	public static float easeInOutCubic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return c / 2 * t * t * t + b;
		return c / 2 * ((t -= 2) * t * t + 2) + b;
	}

	public static float easeInQuart(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * (t /= d) * t * t * t + b;
	}

	public static float easeOutQuart(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return -c * ((t = t / d - 1) * t * t * t - 1) + b;
	}

	public static float easeInOutQuart(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return c / 2 * t * t * t * t + b;
		return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
	}
	
	public static float easeInQuint(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * (t /= d) * t * t * t * t + b;
	}

	public static float easeOutQuint(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
	}

	public static float easeInOutQuint(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return c / 2 * t * t * t * t * t + b;
		return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
	}

	public static float easeInSine(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) (-c * Math.cos(t / d * (Math.PI / 2)) + c + b);
	}

	public static float easeOutSine(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) (c * Math.sin(t / d * (Math.PI / 2)) + b);
	}

	public static float easeInOutSine(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) (-c / 2 * (Math.cos(Math.PI * t / d) - 1) + b);
	}

	public static float easeInExpo(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) ((t == 0) ? b : c * Math.pow(2, 10 * (t / d - 1)) + b);
	}

	public static float easeOutExpo(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) ((t == d) ? b + c : c * (-Math.pow(2, -10 * t / d) + 1) + b);
	}

	public static float easeInOutExpo(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if(t == 0)
			return b;
		if((t /= d / 2) < 1)
			return (float) (c / 2 * Math.pow(2, 10 * (t - 1)) + b);
		return (float) (c / 2 * (-Math.pow(2, -10 * --t) + 2) + b);
	}

	public static float easeInCirc(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) (-c * (Math.sqrt(1 - (t /= d) * t) - 1) + b);
	}

	public static float easeOutCirc(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return (float) (c * Math.sqrt(1 - (t = t / d - 1) * t) + b);
	}

	public static float easeInOutCirc(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return (float) (-c / 2 * (Math.sqrt(1 - t * t) - 1) + b);
		return (float) (c / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + b);
	}

	public static float easeInElastic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		float s = 1.70158f;
		float p = 0;
		float a = c;
		if(t == 0)
			return b;
		if((t /= d) == 1)
			return b + c;
		if(p == 0)
			p = d * .3f;
		if(a < Math.abs(c)) {
			a = c;
			s = p / 4;
		} else
			s = (float) (p / (2 * Math.PI) * Math.asin(c / a));
		return (float) (-(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b);
	}

	public static float easeOutElastic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		float s = 1.70158f;
		float p = 0;
		float a = c;
		if(t == 0)
			return b;
		if((t /= d) == 1)
			return b + c;
		if(p == 0)
			p = d * .3f;
		if(a < Math.abs(c)) {
			a = c;
			s = p / 4;
		} else
			s = (float) (p / (2 * Math.PI) * Math.asin(c / a));
		return (float) (a * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b);
	}

	public static float easeInOutElastic(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		float s = 1.70158f;
		float p = 0;
		float a = c;
		if(t == 0)
			return b;
		if((t /= d / 2) == 2)
			return b + c;
		if(p == 0)
			p = d * (.3f * 1.5f);
		if(a < Math.abs(c)) {
			a = c;
			s = p / 4;
		} else
			s = (float) (p / (2 * Math.PI) * Math.asin(c / a));
		if(t < 1)
			return (float) (-.5 * (a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b);
		return (float) (a * Math.pow(2, -10 * (t -= 1)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * .5 + c + b);
	}

	public static float easeInBack(float t, float b, float c, float d) {
		return easeInBack(t, b, c, d, 1.70158f);
	}

	public static float easeInBack(float t, float b, float c, float d, float s) {
		if(t >= d) {
			return b + c;
		}
		return c * (t /= d) * t * ((s + 1) * t - s) + b;
	}

	public static float easeOutBack(float t, float b, float c, float d) {
		return easeOutBack(t, b, c, d, 1.70158f);
	}

	public static float easeOutBack(float t, float b, float c, float d, float s) {
		if(t >= d) {
			return b + c;
		}
		return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
	}

	public static float easeInOutBack(float t, float b, float c, float d) {
		return easeInOutBack(t, b, c, d, 1.70158f);
	}

	public static float easeInOutBack(float t, float b, float c, float d, float s) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d / 2) < 1)
			return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
		return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
	}

	public static float easeInBounce(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		return c - easeOutBounce(d - t, 0, c, d) + b;
	}

	public static float easeOutBounce(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if((t /= d) < (1 / 2.75)) {
			return (float) (c * (7.5625 * t * t) + b);
		} else if(t < (2 / 2.75)) {
			return (float) (c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b);
		} else if(t < (2.5 / 2.75)) {
			return (float) (c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b);
		} else {
			return (float) (c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b);
		}
	}

	public static float easeInOutBounce(float t, float b, float c, float d) {
		if(t >= d) {
			return b + c;
		}
		if(t < d / 2)
			return easeInBounce(t * 2, 0, c, d) * .5f + b;
		return easeOutBounce(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
	}

	public static float easeOutLinear(float t, float c, float d) {
		if(t >= d) {
			return 0;
		}
		return d - t > c ? 1 : 1 - (t - (d - c)) / c;
	}

	public static float easeInLinear(float t, float d) {
		if(t >= d) {
			return 1;
		}
		return t / d;
	}

}
