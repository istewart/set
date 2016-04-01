public class Game {
	private static final int INITIAL_HEIGHT = 4;
	private static final int INITIAL_WIDTH = 3;

	private final int score = 0;

	private final List<Card> deck = new ArrayList<>();
	private final List<List<Card>> field;

	public Game() {
		for (Shape s : Shape.values()) {
			for (Color c : Color.values()) {
				for (Shade sh : Shade.values()) {
					for (Number n : Number.values()) {
						deck.add(new Card(s, c, sh, n));
					}
				}
			}
		}

		Collections.shuffle(deck);

		this.field = new ArrayList<>();

		for (int i = 0; i < INITIAL_HEIGHT; i++) {
			this.field.add(new ArrayList<>());
			for (int j = 0; j < INITIAL_WIDTH; j++) {
				this.field.get(j).add(deck.remove(0));
			}
		}
	}

	public Card getCard(Coord coord) {
		return field.get(coord.row).get(coord.col);
	}

	public Card draw() {
		if (!deck.isEmpty()) {
			return deck.remove(0);
		} else {
			return null;
		}
	}

	public boolean match(Coord coord1, Coord coord2, Coord coord3) {
		Card c1 = this.getCard(coord1);
		Card c2 = this.getCard(coord2);
		Card c3 = this.getCard(coord3);

		int r1 = field.get(coord1.row);
		int r2 = field.get(coord2.row);
		int r3 = field.get(coord3.row);
		
		boolean match = c1.match(c2, c3);

		if (match) {
			r1.remove(c1);
			r2.remove(c2);
			r3.remove(c3);

			
			r1.add(draw());
			r2.add(draw());
			r3.add(draw());

			score += 1;
		}

		return match;
	}

	public List<Coord> hint() {
		List<Card> flatField = new ArrayList<>();
		List<Coord> toReturn = new ArrayList<>();

		for (List<Card> row : field) {
			flatField.addAll(row);
		}

		for (int i = 0; i < flatField.length(); i++) {
			for (j = i + 1; j < flatField.length(); j++) {
				for (k = 0; k < flatField.length(); k++) {
					if (flatField.get(i).match(flatField.get(j), flatField(k))) {
						toReturn.add(new Coord(i / INITIAL_WIDTH, i % INITIAL_WIDTH));
						toReturn.add(new Coord(j / INITIAL_WIDTH, j % INITIAL_WIDTH));
						toReturn.add(new Coord(k / INITIAL_WIDTH, k % INITIAL_WIDTH));

						return toReturn;
					}
				}
			}
		}

		return toReturn;
	}
}