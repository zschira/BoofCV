/*
 * Copyright (c) 2011-2016, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.distort.spherical;

import boofcv.struct.distort.PixelTransform_F64;
import georegression.misc.GrlConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Peter Abeles
 */
public class TestEquirectangularRotate_F64 {

	/**
	 * Sees if recentering moves it to approximately the expected location
	 */
	@Test
	public void simpleTests() {

		EquirectangularRotate_F64 alg = new EquirectangularRotate_F64();
		alg.setImageShape(300,250);

		// this is the standard configuration and there should be no change
		alg.setDirection(0,0,0);
		alg.compute((int)(300.0*0.5), (int)(250*0.5));
		assertMatch( alg, 300.0*0.5, 250*0.5);

		alg.setDirection( Math.PI/2.0,0,0);
		alg.compute((int)(300.0*0.5), (int)(250*0.5));
		assertMatch( alg, 300.0*0.75, 250*0.5);

		alg.setDirection(0, Math.PI/4.0,0);
		alg.compute((int)(300.0*0.5), (int)(250*0.5));
		assertMatch( alg, 300.0*0.5, 250*0.75);
	}

	private void assertMatch(PixelTransform_F64 tran , double x , double y ) {
		assertEquals( x , tran.distX, GrlConstants.DOUBLE_TEST_TOL);
		assertEquals( y , tran.distY, GrlConstants.DOUBLE_TEST_TOL);
	}
}
