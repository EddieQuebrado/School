from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import sys
import random

class CommentAutomation:
    username = 'EduAnimals'
    password = 'EQ8TK5JR3'

    hashtags = [
        'animals_in_world', 'animal_fanatics', 'animalelite', 'animal_captures', 'animal_sultans',
        'wildlife_seekers', 'wildlifephotos', 'wildlifefriend', 'wildlifeowners', 'majestic_wildlife_',
    ]

    comments = [ 'Cool Content bro!', 'Nice! If youre interested in more photos like this give us a follow', 'Cool post! If you want to learn about animals follow our page', 'What an awesome post!',
    'Like this post, Youll love our page! give us a follow', 'Wow! Thats awesome!', 'Love animals? give our page a follow!','Wow, love thie page', 'Check out our page, we post educational posts daily about animals!',
    'Wow this post was awesome, if youre interested in this post follow our account for more content like this.','We are an Instagram page dedicated to educating people on the animals of the world, if its not an issue we would appreciate if you could give us a follow. Thank you.','Great job on the post!, we post similar content if you could give us a follow that would be appreciated',
    'We see that you like animals, we post similar content. Check us out!','We are new to Instagram we would appreciate it if you could check out our page, we post educational posts on animals.','Hey there! We are a page dedicated towards educating people about animals. Check us out if you are interested!','We are dedicated to spreading information on animals and would appreciated if you check us out.',
    'If you are interested in learning about the animals of our planet give us a follow!','This is a awesome post, we love to see the world engaging in learning about animals too.','This is a great post!','We love that you are spreading awareness #ForTheFuture','Wow this is a cool post, Greate job',
    'Love you content! Check out our page about animals!', 'Our page @eduanimals is all about educating others on the subject of animals, I think you will really love it!', 'Come take a look at our page @eduanimals ! we post daily!', 'We @eduanimals post daily educational animal facts and would appreciate if you would chekc out our page!',
    'Let us all get spread good love and good vibes @eduanimal', 'Even National Geographic would be proud, good job!', 'To the photographer of this photo, Thank you', 'Learn about the animals of our planet with us @eduanimals', 'What is the conservation status on the Addax Antelope? Find out the answer on @eduanimals', 'I love posts like these!',
    'I predict a successful future to you all, we need to spread love and goodvibes to the world.', 'Positivity is the number one and this post definitely brought positivity into this world.',
    'Information on the animals of the world is at your grasp @eduanimals', 'Nothing more precious', 'Our furry friends are cute', 'The best post are here @eduanimals',
    'Are you passionate about animals? If so follow us @eduanimals to learn more about our furry friends', 'Join our community @eduanimals',
    ]

    links = []

    price = 0.0

    def __init__(self):
        self.browser = webdriver.Firefox(executable_path="~/python-workspace\\InstagramAutomation\\geckodriver.exe")
        self.login()
        self.hustle()

    def login(self):
        self.browser.get('https://www.instagram.com/accounts/login/?source=auth_switcher')
        time.sleep(2)

        username_field = self.browser.find_element_by_xpath("//input[@name='username']")
        username_field.clear()
        username_field.send_keys(self.username)
        time.sleep(1)

        passwords_field = self.browser.find_element_by_xpath("//input[@name='password']")
        passwords_field.clear()
        passwords_field.send_keys(self.password)
        time.sleep(1)

        login_button = self.browser.find_element_by_xpath("//button[@type='submit']")
        login_button.click()
        time.sleep(2)

    def hustle(self):
        self.getTopPosts()
        self.execute()
        self.finalize()

    def getTopPosts(self):
        for hashtag in self.hashtags:
            self.browser.get('https://www.instagram.com/explore/tags/' + hashtag +'/')
            time.sleep(2)

            links = self.browser.find_elements_by_tag_name('a')
            condition = lambda link: '.com/p/' in link.get_attribute('href')
            valid_links = list(filter(condition, links))

            for i in range(0,9):
                link = valid_links[i].get_attribute('href')
                if link not in self.links:
                    self.links.append(link)

    def execute(self):
        for link in self.links:
            self.browser.get(link)
            time.sleep(1)

            self.browser.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            time.sleep(1)

            self.comment()
            time.sleep(2)

            self.like()

            self.price += 0.02
            sleeptime = random.randint(18,28)
            time.sleep(sleeptime)

    def comment(self):
        comment_input = lambda: self.browser.find_element_by_tag_name('textarea')
        comment_input().click()
        comment_input().clear()

        comment = random.choice(self.comments)
        for letter in comment:
            comment_input().send_keys(letter)
            delay = random.randint(1, 7) / 30
            time.sleep(delay)

        comment_input().send_keys(Keys.RETURN)

    def like(self):
        like_button = lambda: self.browser.find_element_by_xpath('//span[@class="glyphsSpriteHeart__outline__24__grey_9 u-__7"]')
        like_button().click()

    def finalize(self):
        print('You gave $' + self.price + " back to the community")
        self.browser.close()
        sys.exit()

commentAutomation = CommentAutomation()