package reservationsystem

class IntegrationTestFixtures {

  static account = {
    return new Account(
            cardId: 123,
            flagForDeletion: false
    )
  }

  static person = {
    def account = IntegrationTestFixtures.account()
    def person = new Person(
            firstName: 'Foo',
            lastName: 'Bar',
            email: 'foo@bar.com',
            phoneNumber: 1234567890,
            userLogin: IntegrationTestFixtures.userLogin(),
            account: account
    )
    account.owner = person
    saveDomain(person)
    saveDomain(account)
    return person
  }

  static userLogin = {
    def userLogin = new UserLogin(
            username: 'testUsername',
            password: 'testPassword',
            enabled: true,
            accountExpired: false,
            accountLocked: false,
            passwordExpired: false
    )
    saveDomain(userLogin)
    return userLogin
  }

  static fine = { account ->
    def fine = new Fine(
            amountOwed: 2.49,
            offense: 'whoCares',
            description: 'no one',
            accoount: account
    )
    account.addToFines(fine)
    saveDomain(fine)
    saveDomain(account)
    return fine
  }

  static createMedia = {
    IntegrationTestFixtures.createBooks()
    IntegrationTestFixtures.createAudio()
    IntegrationTestFixtures.createVideo()
  }

  static createBooks = {
    def books1 = [
            "Unbroken: A World War II Story of Survival, Resilience, and Redemption                                                                                                           ":
            "Laura Hillenbrand                                                                                                                                                                ",
            "Heaven is for Real: A Little Boy's Astounding Story of His Trip to Heaven and Back                                                                                               ":
            "Todd Burpo, Sonja Burpo, Colton Burpo, Lynn Vincent                                                                                                                              ",
            "The 4-Hour Body: An Uncommon Guide to Rapid Fat-Loss, Incredible Sex, and Becoming Superhuman                                                                                    ":
            "Timothy Ferriss                                                                                                                                                                  ",
            "A Discovery of Witches: A Novel                                                                                                                                                  ":
            "Deborah E. Harkness                                                                                                                                                              ",
            "Slow Cooker Revolution                                                                                                                                                           ":
            "The Editors at America's Test Kitchen, America's Test Kitchen                                                                                                                    ",
            "Cutting for Stone                                                                                                                                                                ":
            "Abraham Verghese                                                                                                                                                                 ",
            "The Girl Who Kicked the Hornet's Nest                                                                                                                                            ":
            "Stieg Larsson, Reg Keeland                                                                                                                                                       ",
            "Peace from Broken Pieces: How to Get Through What You're Going Through                                                                                                           ":
            "Iyanla Vanzant                                                                                                                                                                   ",
            "The Wise Man's Fear (Kingkiller Chronicles, Day 2)                                                                                                                               ":
            "Patrick Rothfuss                                                                                                                                                                 ",
            "Total Body Breakthroughs: The World's Leading Experts Reveal Proven Health, Fitness & Nutrition Secrets To Help You Achieve The Body You've Always Wanted But Couldn't Until Now!":
            "The Fitness Elite, Dax Moy                                                                                                                                                       ",
            "Angel: A Maximum Ride Novel (Maximum Ride: The Protectors)                                                                                                                       ":
            "James Patterson                                                                                                                                                                  ",
            "The 5 Love Languages: The Secret to Love That Lasts                                                                                                                              ":
            "Gary Chapman                                                                                                                                                                     ",
            "StrengthsFinder 2.0                                                                                                                                                              ":
            "Tom Rath                                                                                                                                                                         ",
            "Dead Reckoning (Sookie Stackhouse, Book 11)                                                                                                                                      ":
            "Charlaine Harris                                                                                                                                                                 ",
            "Known and Unknown: A Memoir                                                                                                                                                      ":
            "Donald Rumsfeld                                                                                                                                                                  ",
            "Water for Elephants: A Novel                                                                                                                                                     ":
            "Sara Gruen                                                                                                                                                                       ",
            "How the West Was Lost: Fifty Years of Economic Folly--and the Stark Choices Ahead                                                                                                ":
            "Dambisa Moyo                                                                                                                                                                     ",
            "The Girl with the Dragon Tattoo                                                                                                                                                  ":
            "Stieg Larsson, Reg Keeland                                                                                                                                                       ",
            "The Help                                                                                                                                                                         ":
            "Kathryn Stockett                                                                                                                                                                 ",
            "Battle Hymn of the Tiger Mother                                                                                                                                                  ":
            "Amy Chua                                                                                                                                                                         ",
            "Publication Manual of the American Psychological Association, Sixth Edition                                      ":
            "American Psychological Association                                                                               ",
            "In the Blink of an Eye: Dale, Daytona, and the Day that Changed Everything                                       ":
            "Michael Waltrip, Ellis Henican                                                                                   ",
            "The Coming Insurrection (Semiotext(e) / Intervention)                                                            ":
            "The Invisible Committee                                                                                          ",
            "Mockingjay (The Final Book of The Hunger Games)                                                                  ":
            "Suzanne Collins                                                                                                  ",
            "One Thousand Gifts: A Dare to Live Fully Right Where You Are                                                     ":
            "Ann Voskamp                                                                                                      ",
            "Veganist: Lose Weight, Get Healthy, Change the World                                                             ":
            "Kathy Freston                                                                                                    ",
            "Room: A Novel                                                                                                    ":
            "Emma Donoghue                                                                                                    ",
            "The Girl Who Played with Fire (Vintage)                                                                          ":
            "Stieg Larsson, Reg Keeland                                                                                       ",
            "The Big Short: Inside the Doomsday Machine                                                                       ":
            "Michael Lewis                                                                                                    ",
            "Catching Fire (The Second Book of the Hunger Games)                                                              ":
            "Suzanne Collins                                                                                                  ",
            "Silverlicious (Pinkalicious)                                                                                     ":
            "Victoria Kann                                                                                                    ",
            "Cleopatra: A Life                                                                                                ":
            "Stacy Schiff                                                                                                     ",
            "The 4-Hour Workweek, Expanded and Updated: Expanded and Updated, With Over 100 New Pages of Cutting-Edge Content.":
            "Timothy Ferriss                                                                                                  ",
            "Tick Tock (Michael Bennett)                                                                                      ":
            "James Patterson, Michael Ledwidge                                                                                ",
            "Hotel on the Corner of Bitter and Sweet                                                                          ":
            "Jamie Ford                                                                                                       ",
            "Crazy Love: Overwhelmed by a Relentless God                                                                      ":
            "Francis Chan                                                                                                     ",
            "True You: A Journey to Finding and Loving Yourself                                                               ":
            "Janet Jackson, David Ritz                                                                                        ",
            "The Islamic Antichrist: The Shocking Truth about the Real Nature of the Beast                                    ":
            "Joel Richardson                                                                                                  ",
            "The Investment Answer                                                                                            ":
            "Daniel C. Goldie, Gordon S. Murray                                                                               ",
            "Swamplandia!                                                                                                     ":
            "Karen Russell                                                                                                    "
    ]

    def books = []
    books1.each { k, v ->
      books << [title: k.trim(), author: v.trim()]
    }

    books.each {
      saveDomain(new Print(
              type: MediaType.PRINT,
              format: MediaFormat.EPUB,
              title: it.title,
              artist: it.author
      ))
      saveDomain(new Print(
              type: MediaType.PRINT,
              format: MediaFormat.PAPER,
              title: it.title,
              artist: it.author
      ))
    }
  }

  static createVideo = {
    def video1 = [
            "Inception                                                                                                                 ": [actor:
            "        Leonardo DiCaprio (Actor), Ken Watanabe (Actor),                                                                  ", director:
            "        Christopher Nolan (Director)                                                                                      "],
            "Toy Story 3                                                                                                               ": [actor:
            "        Tom Hanks (Actor), Tim Allen (Actor),                                                                             ", director:
            "        Lee Unkrich (Director)                                                                                            "],
            "Harry Potter and the Deathly Hallows, Part 1                                                                              ": [actor:
            "        Daniel Radcliffe (Actor), Rupert Grint (Actor),                                                                   ", director:
            "        David Yates (Director)                                                                                            "],
            "Iron Man 2                                                                                                                ": [actor:
            "        Robert Jr. Downey (Actor), Don Cheadle (Actor)                                                                    "],
            "Secretariat                                                                                                               ": [actor:
            "        Diane Lane (Actor), John Malkovich (Actor)                                                                        "],
            "Unstoppable                                                                                                               ": [actor:
            "        Chris Pine (Actor), Denzel Washington (Actor),                                                                    ", director:
            "        Tony Scott (Director)                                                                                             "],
            "Tangled                                                                                                                   ": [actor:
            "        Mandy Moore (Actor), Zachary Levi (Actor)                                                                         "],
            "Jillian Michaels - 30 Day Shred                                                                                           ": [actor:
            "        Jillian Michaels (Actor),                                                                                         ", director:
            "        Andrea Ambandos (Director)                                                                                        "],
            "Red                                                                                                                       ": [actor:
            "        Bruce Willis (Actor), Morgan Freeman (Actor),                                                                     ", director:
            "        Robert Schwentke (Director)                                                                                       "],
            "Tangled                                                                                                                   ": [actor:
            "        Mandy Moore (Actor), Zachary Levi (Actor)                                                                         "],
            "Masterpiece Classic: Downton Abbey                                                                                        ": [:],
            "How to Train Your Dragon                                                                                                  ": [actor:
            "        Jay Baruchel (Actor), Gerard Butler (Actor),                                                                      ", director:
            "        Dean DeBlois and Chris Sanders (Director)                                                                         "],
            "The King's Speech                                                                                                         ": [actor:
            "        Colin Firth (Actor), Helena Bonham Carter (Actor),                                                                ", director:
            "        Tom Hooper (Director)                                                                                             "],
            'Waiting for "Superman"                                                                                                    ': [actor:
            "        Geoffrey Canada (Actor), Michelle Rhee (Actor),                                                                   ", director:
            "        Davis Guggenheim (Director)                                                                                       "],
            "The Town                                                                                                                  ": [actor:
            "        Ben Affleck (Actor), Rebecca Hall (Actor),                                                                        ", director:
            "        Ben Affleck (Director)                                                                                            "],
            "The Social Network                                                                                                        ": [actor:
            "        Jesse Eisenberg (Actor), Andrew Garfield (Actor),                                                                 ", director:
            "        David Fincher (Director)                                                                                          "],
            "Beauty and the Beast                                                                                                      ": [:]
    ]

    def video = []
    video1.each { k, v ->
      video << [title: k.trim(), actor: v?.actor?.trim(), director: v?.director?.trim()]
    }

    video.each {
      saveDomain(new Video(
              type: MediaType.VIDEO,
              format: MediaFormat.AVI,
              title: it.title,
              artist: it.actor,
              director: it.director
      ))

      saveDomain(new Video(
              type: MediaType.VIDEO,
              format: MediaFormat.DVD,
              title: it.title,
              artist: it.actor,
              director: it.director
      ))


    }
  }

  static createAudio = {
    def audio1 = [
            "Sigh No More ~ Mumford & Sons                            ",
            "21 ~ Adele                                               ",
            "Need You Now ~ Lady Antebellum                           ",
            "2011 GRAMMY Nominees ~ Various Artists (Artist)          ",
            "The Suburbs ~ Arcade Fire                                ",
            "Chamber Music Society ~ Esperanza Spalding               ",
            "The King Is Dead ~ The Decemberists                      ",
            "Let England Shake ~ PJ Harvey                            ",
            "Brothers ~ The Black Keys                                ",
            "Doo - Wops & Hooligans ~ Bruno Mars                      ",
            "Low Country Blues ~ Gregg Allman                         ",
            "Mission Bell ~ Amos Lee                                  ",
            "Esperanza ~ Esperanza Spalding                           ",
            "Lungs ~ Florence + The Machine                           ",
            "Blessed ~ Lucinda Williams                               ",
            "Recovery ~ Eminem                                        ",
            "The Lady Killer ~ Cee-Lo Green                           ",
            "I and Love and You ~ The Avett Brothers                  ",
            "Go-Go Boots ~ Drive-By Truckers                          ",
            "The Resistance ~ Muse                                    ",
            "The People's Key ~ Bright Eyes                           ",
            "19 ~ Adele                                               ",
            "Heritage ~ Celtic Thunder                                ",
            "Teenage Dream ~ Katy Perry                               ",
            "Lullaby ~ Celtic Woman                                   ",
            "God Willin' & The Creek Don't Rise ~ Ray LaMontagne      ",
            "Greatest Hits... So Far!!! ~ Pink                        ",
            "Kiss Each Other Clean ~ Iron & Wine                      ",
            "Late Nights, Early Mornings ~ Marsha Ambrosius           ",
            "Speak Now ~ Taylor Swift                                 ",
            "You Get What You Give ~ Zac Brown Band                   ",
            "KMAG YOYO (& other American stories) ~ Hayes Carll       ",
            "Collapse Into Now ~ R.E.M.                               ",
            "Glee: The Music, Volume 4 ~ Glee Cast                    ",
            "Save Me, San Francisco (Golden Gate Edition) ~ Train     ",
            "Illuminations ~ Josh Groban                              ",
            "My World 2.0 ~ Justin Bieber                             ",
            "The Covering ~ Stryper                                   ",
            "Sigh No More ~ Mumford & Sons                            ",
    ]

    def audio = []
    audio1.each {
      def tempMap = it.trim().split('~')
      audio << [artist: tempMap[1].trim(), album: tempMap[0].trim()]
    }

    audio.each {
      saveDomain(new Audio(
              type: MediaType.AUDIO,
              format: MediaFormat.MP3,
              title: it.album,
              artist: it.artist
      ))

      saveDomain(new Audio(
              type: MediaType.AUDIO,
              format: MediaFormat.CD,
              title: it.album,
              artist: it.artist
      ))

    }
  }


  static saveDomain(domain) {
    if (!domain.save(flush: true)) {
      println "Domain failed to save! " + domain.errors.allErrors.toString()
      fail(domain.errors.allErrors.toString())
    }
    return domain
  }

}
