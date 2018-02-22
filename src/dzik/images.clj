(ns dzik.images)

(defn open-image [path]
  (javax.imageio.ImageIO/read
   (java.io.File. path)))

(defn save-image-as [original-image path]
  ;; ImageIO.write(image, "png", new File("test.png"));
  (println "called with" path)
  (javax.imageio.ImageIO/write original-image "png" (java.io.File. path))
  path)
  
(defn rand-str [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn with-image [path opers]
  (let [image (open-image path)]
    (opers (.getGraphics image))
    (save-image-as image (str path (rand-str 5) ".png"))))



(defn count-x-position [center font-width length]
  (- center (* font-width (int (/ length 2)))))

(defn add-text [path text]
  (with-image path
    (fn [graphics]
      (let [font (-> graphics .getFont ( .deriveFont (float 32)))]
        ;;(.setFont graphics font)
        (.setColor graphics (java.awt.Color. 77 148 24))
        (.setFont graphics (java.awt.Font. java.awt.Font/MONOSPACED java.awt.Font/PLAIN, 20))
        (.drawString graphics text (count-x-position 100 10 (count text)) 70)
        (.dispose graphics)))))



(defn add-text-get-url [text]
  (let [result
        (clojure.string/replace
         (add-text "/home/mattermost/cows/cow-t.png" text) "/home/mattermost/cows/" "http://chat.sentione.com:1337/")]
    (java.lang.Thread/sleep 4000)
    result))


;;(add-text-get-url "mooo")

;; cow max 28
;;(clojure.string/replace (add-text "/home/chimeo/cows/cow-t.png" "moooooooo") "/home/chimeo/cows/" "http://chat.sentione.com:1337/")
