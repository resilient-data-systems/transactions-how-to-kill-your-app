import threading
import urllib2
import sys


def crawl(numOfLoops, url):

    for i in range(numOfLoops):
        urllib2.urlopen(url)


def startCrawlers(numOfCrawlers, numOfLoops,url):
    threads = []
    for n in range(numOfCrawlers):
        thread = threading.Thread(target=crawl, args=(numOfLoops,url,))
        thread.start()

        threads.append(thread)

    print "getting there..."

    for thread in threads:
        thread.join()

    print "done"

def main():
    crawlers = int(sys.argv[1])
    loops = int(sys.argv[2])
    url = sys.argv[3]

    startCrawlers(crawlers, loops, url)


if __name__ == '__main__':
    main()