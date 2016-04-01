public class Card {
	private final Shape shape;
	private final Color color;
	private final Shade shade;
	private final Number number;

	public Card(Shape shape, Color color, Shade shade, Number number) {
		this.shape = shape;
		this.color = color;
		this.shade = shade;
		this.number = number;
	}

	public boolean match(Card second, Card third) {
		boolean shape = this.shape == second.shape && second.shape == third.shape ||
		                this.shape != second.shape && this.shape != third.shape && second.shape != third.shape; 

        boolean color = this.color == second.color && second.color == third.color ||
		                this.color != second.color && this.color != third.color && second.color != third.color; 

        boolean shade = this.shade == second.shade && second.shade == third.shade ||
		                this.shade != second.shade && this.shade != third.shade && second.shade != third.shade;

        boolean number = this.number == second.number && second.number == third.number ||
		                this.number != second.number && this.number != third.number && second.number != third.number;

		return shape && color && shade && number;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof Card) {
			Card that = (Card) o;
			return (this.shape == that.shape && this.color == that.color && this.number == that.number && this.shade == that.shade);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		throw new NotYetImplementedException();
	}
}