(ns dzik.images)

(defn open-image [path]
  (javax.imageio.ImageIO/read
   (java.io.File. path)))

(defn save-image-as [original-image path]
  ;; ImageIO.write(image, "png", new File("test.png"));
  (println "called with" path)
  (javax.imageio.ImageIO/write original-image "jpg" (java.io.File. path))
  path)
  

(defn with-image [path opers]
  (let [image (open-image path)]
    (opers (.getGraphics image))
    (save-image-as image (str path "-copy.jpg"))))



(defn count-x-position [center font-width length]
  (- center (* font-width (int (/ length 2)))))

(defn add-text [path text]
  (with-image path
    (fn [graphics]
      (let [font (-> graphics .getFont ( .deriveFont (float 40)))]
        ;;(.setFont graphics font)
        (.setColor graphics java.awt.Color/BLACK)
        (.setFont graphics (java.awt.Font. java.awt.Font/MONOSPACED java.awt.Font/PLAIN, 20))
        (.drawString graphics text (count-x-position 150 10 (count text)) 100)
        (.dispose graphics)))))



;; cow max 28
(add-text "/Users/whr/Desktop/cow.png" "mooooooooooooooooooooooooooo")
