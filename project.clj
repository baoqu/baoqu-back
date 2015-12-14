(defproject baoqu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [liberator "0.10.0"]
                 [cheshire "5.2.0"]
                 [yesql "0.5.1"]]
  :plugins [[lein-ring "0.8.12"]
            [lein-asciidoctor "0.1.14"]]
  :ring {:handler baoqu.handler/app
         :init baoqu.handler/init
         :destroy baoqu.handler/destroy}
  :asciidoctor {:sources "doc/*.adoc"
                :to-dir "target/asciidoctor/"
                :source-highlight true
                :toc :left}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
