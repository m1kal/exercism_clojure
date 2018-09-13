(ns robot-simulator)

(def directions [:north :east :south :west])

(def axis {:east :x :west :x :north :y :south :y})

(def arrow {:east inc :west dec :north inc :south dec})

(defn- turn [direction inc-or-dec]
  (directions (mod (inc-or-dec (.indexOf directions direction)) 4)))

(defn turn-right [direction]
  (turn direction inc))

(defn turn-left [direction]
  (turn direction dec))

(defn- advance [{:keys [bearing] :as robot}]
  (update-in robot [:coordinates (axis bearing)] (arrow bearing)))

(defn- run-command [robot command]
  (condp = command
    \R (update-in robot [:bearing] turn-right)
    \L (update-in robot [:bearing] turn-left)
    \A (advance robot)
    robot))

(defn robot [coordinates direction]
  {:coordinates coordinates :bearing direction})

(defn simulate [commands robot]
  (reduce run-command robot commands))

