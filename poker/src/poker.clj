(ns poker)

(def number-or-figure (juxt {"J" 11 "Q" 12 "K" 13 "A" 14} read-string))

(defn- to-number [element]
  (->> element
       number-or-figure
       (filter identity)
       first))

(defn- card [input]
  (->> input
       (re-matches #"(.*)(.)")
       rest
       (map to-number)
       (zipmap [:value :face])))

(defn- n-cards [n hand]
  (->> hand
       (map :value)
       frequencies
       (filter #(= (val %) n))
       (map first)
       sort
       (map-indexed #(* (Math/pow 16 %1) %2))
       (reduce +)))

(def highest (partial n-cards 1))
(def pair (partial n-cards 2))
(def three (partial n-cards 3))
(def four (partial n-cards 4))

(defn- five-in-a-row [cards]
  (and
    (= 5 (count cards))
    (= 4 (- (last cards) (first cards)))))

(defn- straight [hand]
  (->> hand
       (map :value)
       set
       ((juxt identity #(replace {14 1} %)))
       (map sort)
       (filter five-in-a-row)
       (#(if (empty? %) 0 (last (last %))))))

(defn- flush_ [hand]
  (if (= 1 (count (set (map :face hand)))) 1 0))

(defn both [& conditions]
  (fn [hand]
    (reduce * ((apply juxt conditions) hand))))

(def straight-flush (both straight flush_))
(def full-house (both pair three))

(def hand-rating
  (juxt straight-flush four full-house flush_ straight three pair highest))

(defn- rate [hand-string]
  (hand-rating (map card (clojure.string/split hand-string #" "))))

(defn best-hands [hands]
  (->> hands
       (group-by rate)
       (sort-by first)
       last
       last))

