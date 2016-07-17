package FLOG_LOGIC;

/**
 * UCD ID - 14208891
 * @author Pasindu
 * Contains constant elements.
 */
class ConstantElement extends FlogElement {
  /** Set the constant value. */
  ConstantElement(double value) {
    super.value = value;
  }

  public double getValue() {
    return this.value;
  }
}
